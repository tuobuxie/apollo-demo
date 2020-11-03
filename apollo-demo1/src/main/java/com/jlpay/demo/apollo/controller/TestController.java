package com.jlpay.demo.apollo.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishaofeng
 */
@Slf4j
@RestController
public class TestController {

    @Value( "${server.port}" )
    String port;

    @Value( "${customer.key:''}" )
    String customerKey;

    @GetMapping("getKey/{{key}}")
    public String getKey(@PathVariable("{key}") String key) {

        String value = "null" ;
        return  key + "=" + value + "\n";
    }
    @GetMapping("port")
    public String port() {

        log.debug( "debug log..." );
        log.info( "info log..." );
        log.warn( "warn log..." );

        return "server.port=" + port + "\n";
    }
    @GetMapping("customerKey")
    public String customerKey() {
        return "customer.key=" + customerKey + "\n";
    }
}