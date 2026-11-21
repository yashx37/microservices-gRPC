package com.yash.paymentservice.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class GrpcServer {

    private Server server;

    @EventListener(ApplicationReadyEvent.class)
    public void start() throws IOException{
        server = ServerBuilder
                .forPort(9090)
                .addService(new PaymentGrpcService())
                .build()
                .start();

        System.out.println("server started on port 9090");
    }

    @PreDestroy
    public void stop(){
        if(server != null){
            server.shutdown();
        }
    }
}
