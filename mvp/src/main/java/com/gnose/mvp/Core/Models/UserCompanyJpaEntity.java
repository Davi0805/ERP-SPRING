package com.gnose.mvp.Core.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_companies")
public class UserCompanyJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity userId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyJpaEntity companyId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RolesJpaEntity roleId;
}
