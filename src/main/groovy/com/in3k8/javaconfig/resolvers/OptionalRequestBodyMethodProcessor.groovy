package com.in3k8.javaconfig.resolvers

import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor

class OptionalRequestBodyMethodProcessor extends RequestResponseBodyMethodProcessor {


    OptionalRequestBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters)
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(OptionalRequestBody.class)
    }


    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpInputMessage inputMessage = createInputMessage(webRequest)
        if (inputMessage.headers.getContentLength() > 0 && inputMessage.body) {
            return super.resolveArgument(parameter, mavContainer, webRequest, binderFactory)
        }

    }

}