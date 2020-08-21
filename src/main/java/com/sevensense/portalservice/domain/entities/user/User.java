package com.sevensense.portalservice.domain.entities.user;

import com.sevensense.portalservice.domain.entities.AuditInformation;
import com.sevensense.portalservice.domain.entities.BusinessId;
import com.sevensense.portalservice.domain.entities.user.role.Role;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User extends AuditInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private BusinessId businessId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "user_role",
        joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Collection<Role> roles;

    public void addRole(Role role) {
        if (this.roles == null) {
            this.roles = Collections.singleton(role);
        } else {
            this.roles.add(role);
        }
    }

}