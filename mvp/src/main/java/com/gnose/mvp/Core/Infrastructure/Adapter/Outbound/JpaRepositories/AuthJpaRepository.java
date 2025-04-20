package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories;


import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    @Query(value = """
        SELECT
                    c.id AS company_id,
                    r.name AS role_name,
                    p.code AS permission_code
                FROM users u
                JOIN user_companies uc ON u.id = uc.user_id
                JOIN companies c ON uc.company_id = c.id
                JOIN roles r ON uc.role_id = r.id
                JOIN role_permission rp ON r.id = rp.role_id
                JOIN permissions p ON rp.permission_id = p.id
                WHERE u.id = ?
                ORDER BY c.id, r.name
    """, nativeQuery = true)
    List<Object[]> FindPermissionsByUserId(@Param("userId") Long userId);
}
