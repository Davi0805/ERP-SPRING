package com.gnose.mvp.Notifications_Module.Adapters.outbound;

import com.gnose.mvp.Notifications_Module.Infrastructure.Entities.NotificationJpaEntity;
import com.gnose.mvp.Notifications_Module.Infrastructure.Entities.SeverityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationJpaEntity, Long> {
    Optional<List<NotificationJpaEntity>> findByCompanyIdInOrderByCreatedAtDesc(List<Long> companyId);
    Optional<List<NotificationJpaEntity>> findByCompanyIdInAndSeverityOrderByCreatedAtDesc(List<Long> companyId, SeverityStatus severity);

    // todo: later add a custom query that will make a join to check if user already read or not the notification.
    // todo: idk if left join will create too much overhead, something to search later
}
