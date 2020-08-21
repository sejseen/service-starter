package com.sevensense.portalservice.domain.entities.user.privilege;

import com.sevensense.portalservice.domain.entities.user.role.Role;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "privileges")
@AllArgsConstructor
@NoArgsConstructor
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

}