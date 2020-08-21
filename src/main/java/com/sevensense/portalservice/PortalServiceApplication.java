package com.sevensense.portalservice;

import com.sevensense.portalservice.adapter.external.rest.HTTPRequestSender;
import com.sevensense.portalservice.adapter.external.rest.TestDTORequest;
import com.sevensense.portalservice.adapter.external.rest.TestDTOResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class PortalServiceApplication implements CommandLineRunner {

    @Autowired
    private HTTPRequestSender sender;

    public static void main(String[] args) {
        SpringApplication.run(PortalServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        ResponseSpec test = sender.post("/portal-service/test", new TestDTORequest("testValue", "RequestInf"));
//        ResponseSpec testGet = sender.get("/portal-service/test");
//
//        List<TestDTOResponse> blockGet = testGet.bodyToFlux(TestDTOResponse.class).collectList().block();
//        TestDTOResponse block = test.bodyToMono(TestDTOResponse.class).block();
    }
}
