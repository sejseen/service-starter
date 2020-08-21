package com.sevensense.portalservice.domain.entities.authentication;


import com.sevensense.portalservice.domain.entities.AuditInformation;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "authentication_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationToken extends AuditInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "user_bid", nullable = false)
    private UUID userBid;

    @Column(name = "jwt", nullable = false)
    private String jwt;

    public void expiryNow() {
        this.expirationDate = LocalDateTime.now();
    }

    public boolean isNotExpired() {
        return LocalDateTime.now().isBefore(this.expirationDate);
    }

}
