package com.in3k8.javaconfig.controllers;


import spock.lang.Specification
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import static org.springframework.http.HttpStatus.OK
import com.google.gson.Gson
import com.in3k8.javaconfig.model.GreeterParams
import spock.lang.Unroll

public class GreeterControllerSpec extends Specification {

    GreeterController controller


    def setup() {
        controller = new GreeterController(gson:new Gson())
    }

    @Unroll
    def 'do greet null or blank params - #params' () {

        when:
            ResponseEntity<String> response = controller.doGreet(params)
        then:
            response.statusCode == OK
            response.body == '{"message":"Hello"}'

        where:
            params << [null, new GreeterParams()]


    }


    def 'do greet params' () {

        given:
            GreeterParams params = new GreeterParams(name: 'Yos')

        when:
            ResponseEntity<String> response = controller.doGreet(params)

        then:
            response.statusCode == OK
            response.body == '{"message":"Hello","name":"Yos"}'

    }
}
