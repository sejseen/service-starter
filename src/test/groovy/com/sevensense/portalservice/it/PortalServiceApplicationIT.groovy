package com.sevensense.portalservice.it


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class PortalServiceApplicationIT extends Specification {

    @Autowired
    ApplicationContext context

    def testContext() {
        expect:
        context != null
    }

}
