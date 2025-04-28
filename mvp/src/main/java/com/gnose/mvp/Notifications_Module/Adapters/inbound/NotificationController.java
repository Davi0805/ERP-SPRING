package com.gnose.mvp.Notifications_Module.Adapters.inbound;

import com.gnose.mvp.Core.Adapter.outbound.DTO.CompanyPermissionDTO;
import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import com.gnose.mvp.Core.Application.Impl.RedisServiceImpl;
import com.gnose.mvp.Notifications_Module.Adapters.outbound.NotificationJpaRepository;
import com.gnose.mvp.Notifications_Module.Infrastructure.Entities.NotificationJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    // replace later with a service
    private final NotificationJpaRepository jpaRepository;

    private final RedisServiceImpl redisService;

    @Autowired
    public NotificationController(NotificationJpaRepository jpa,
                                  RedisServiceImpl redis)
    {
        this.jpaRepository = jpa;
        this.redisService = redis;
    }

    @GetMapping
    public ResponseEntity<?> getLastNotifications(@RequestHeader("Authorization") String token)
    {
        try {
            SessionRedisDTO session = redisService.getSession(token.replace("Bearer ", ""));
            List<Long> companies = new ArrayList<>();

            for (CompanyPermissionDTO dto : session.getCompanyPermission())
                companies.add(dto.getCompanyId());

            List<NotificationJpaEntity> notif = jpaRepository.
                    findByCompanyIdInOrderByCreatedAtDesc(companies)
                    .orElseThrow(() -> new RuntimeException("Notifications not found!"));

            return ResponseEntity.ok(notif);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
