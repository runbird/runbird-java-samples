package com.scy.runbirdwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class RunbirdwebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunbirdwebfluxApplication.class, args);
    }
}
