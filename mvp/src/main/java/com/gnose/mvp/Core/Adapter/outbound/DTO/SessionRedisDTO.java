package com.gnose.mvp.Core.Adapter.outbound.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("jwt")
public class SessionRedisDTO implements Serializable {
    @Id
    private String token;
    private List<CompanyPermissionDTO> companyPermission;
}
