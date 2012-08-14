package spec

import groovy.json.JsonSlurper
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import spock.lang.Specification
import spock.lang.Unroll

class GreeterSpec extends Specification {

    @Unroll
    def "post to greeter - [#requestEntity]"() {

        given:
            DefaultHttpClient client = new DefaultHttpClient()
            HttpPost post = new HttpPost('http://localhost:8080/spring-mvc-javaconfig/greet')
            post.addHeader('Content-Type','application/json')
            post.addHeader('Accept','application/json')

        and:
            post.setEntity(requestEntity)
        when:
            HttpResponse response = client.execute(post)

        then:
            response.statusLine.statusCode == 200
            expectedJson == new JsonSlurper().parseText(response.entity.content.text)

        where:

            requestEntity                       | expectedJson
            new StringEntity('')                | [message :'Hello']
            new StringEntity('{"name":"Eli"}')  | [message :'Hello' ,name:'Eli']


    }

}
