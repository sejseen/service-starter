package com.ktyma.portalservice.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessId implements Serializable {

    private static final long serialVersionUID = 6080222107343842967L;

    @Column(name = "business_id", nullable = false)
    private UUID businessIdentifier;

    public static BusinessId withRandomUUID() {
        return new BusinessId(UUID.randomUUID());
    }

    public static BusinessId of(UUID uuid) {
        return new BusinessId(uuid);
    }

}