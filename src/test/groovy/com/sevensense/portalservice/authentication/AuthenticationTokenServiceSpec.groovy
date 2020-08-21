package com.sevensense.portalservice.authentication

import com.sevensense.portalservice.domain.entities.authentication.AuthenticationToken
import com.sevensense.portalservice.domain.jpa.repository.authentication.AuthenticationTokenRepository
import spock.lang.Specification
import spock.lang.Subject

class AuthenticationTokenServiceSpec extends Specification {

    def repository = Mock(AuthenticationTokenRepository)

    @Subject
    def service = new AuthenticationTokenService(repository)

    def 'should create new token'() {
        when:
        service.createNew(UUID.randomUUID(), new Date(9999999000), 'jwtstring#123_azx')

        then:
        noExceptionThrown()
        1 * repository.save(_ as AuthenticationToken)
    }

    def 'should expire token with current date'() {
        given:
        def userBid = UUID.randomUUID()

        and:
        def token = GroovyMock(AuthenticationToken)
        repository.findTopByUserBidOrderByIdDesc(userBid) >> Optional.of(token)

        when:
        service.expire(userBid)

        then:
        noExceptionThrown()
        1 * token.expiryNow()
        1 * repository.save(_ as AuthenticationToken)
    }

}
