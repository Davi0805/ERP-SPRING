package com.gnose.mvp.Core.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class RolePermissionsJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RolesJpaEntity roleId;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private PermissionsJpaEntity permission;
}
