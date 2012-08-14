package com.in3k8.javaconfig.resolvers;


import org.springframework.core.MethodParameter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.method.support.ModelAndViewContainer
import spock.lang.Specification

import java.lang.reflect.Method
import javax.servlet.http.HttpServletResponse

class OptionalRequestBodyMethodProcessorSpec extends Specification {

    ServletWebRequest webRequest
    MethodParameter paramRequestBodyString
    ModelAndViewContainer mavContainer
    MockHttpServletRequest servletRequest = new MockHttpServletRequest()

    OptionalRequestBodyMethodProcessor processor


    HttpServletResponse servletResponse = new MockHttpServletResponse()

    def setup() {
        processor = new OptionalRequestBodyMethodProcessor([new StringHttpMessageConverter()])

        Method handle = getClass().getMethod("handle1", String.class)
        paramRequestBodyString = new MethodParameter(handle, 0);

        mavContainer = new ModelAndViewContainer();

    }

    def 'handle empty request body'() {

        given:
            webRequest = new ServletWebRequest(servletRequest, servletResponse)

        when:
            String resolvedContent = processor.resolveArgument(paramRequestBodyString, mavContainer, webRequest, null)

        then:
            null == resolvedContent

    }

    def 'handle request body'() {

        given:
            String actualContent = 'hello'

        and:
            servletRequest.content = actualContent.bytes
            servletRequest.addHeader('Content-Length', actualContent.bytes.length)
            webRequest = new ServletWebRequest(servletRequest, servletResponse)

        when:
            String resolvedContent = processor.resolveArgument(paramRequestBodyString, mavContainer, webRequest, null)

        then:
            actualContent == resolvedContent

    }

    public String handle1(@OptionalRequestBody String s) {
        return s
    }

}
