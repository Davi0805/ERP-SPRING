package com.gnose.mvp.Core.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RolesJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // company_id relationship
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyJpaEntity company;

    @OneToMany(mappedBy = "role")
    private List<UserCompanyJpaEntity> userCompanies;

    @OneToMany(mappedBy = "role")
    private List<RolePermissionsJpaEntity> rolePermissions;
}
