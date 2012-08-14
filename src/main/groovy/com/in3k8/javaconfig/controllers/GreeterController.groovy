package com.in3k8.javaconfig.controllers

import com.google.gson.Gson
import com.in3k8.javaconfig.model.GreeterParams
import com.in3k8.javaconfig.resolvers.OptionalRequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import static org.springframework.http.HttpStatus.OK
import static org.springframework.web.bind.annotation.RequestMethod.POST

@Controller
class GreeterController {

    @Autowired Gson gson

    @RequestMapping(value="/greet", method=POST)
    ResponseEntity<String> doGreet(@OptionalRequestBody GreeterParams params) {
        Map<String, String> map = [message: 'Hello']
        if (params && params.name) {
            map.name = params.name
        }
        return new ResponseEntity<String>( gson.toJson(map) ,OK)
    }
}
