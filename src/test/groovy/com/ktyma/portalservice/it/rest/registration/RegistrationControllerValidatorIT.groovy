package com.ktyma.portalservice.it.rest.registration

import com.ktyma.portalservice.adapter.internal.rest.registration.RegistrationControllerValidator
import com.ktyma.portalservice.adapter.internal.rest.registration.model.RegisterRequest
import com.ktyma.portalservice.domain.entities.user.role.RoleType
import com.ktyma.portalservice.registration.RegistrationCommandService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SuppressWarnings("GroovyAccessibility")
@SpringBootTest
class RegistrationControllerValidatorIT extends Specification {

    @Autowired
    RegistrationCommandService registrationCommandService

    @Autowired
    RegistrationControllerValidator validator

    def 'should validate registration process with success'() {
        given:
        def request = new RegisterRequest(username, password, email, roles as Set)

        when:
        def result = validator.validate(request)

        then:
        noExceptionThrown()
        result.isEmpty()

        where:
        username | password   | email               | roles
        'kty'    | 'kty_pass' | 'kty@spyrosoft.com' | [RoleType.ROLE_SUPER_ADMIN]
    }

    def 'should validate registration process with failure'() {
        given:
        def request = new RegisterRequest(username, password, duplicatedEmail, roles as Set)

        and:
        registrationCommandService.register(request)

        when:
        def result = validator.validate(request)

        then:
        noExceptionThrown()
        result.isPresent()

        where:
        username | password   | duplicatedEmail                  | roles
        'kty'    | 'kty_pass' | 'duplicated_email@spyrosoft.com' | [RoleType.ROLE_SUPER_ADMIN]
    }

}
