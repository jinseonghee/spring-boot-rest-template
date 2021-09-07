package com.example.client.service;


import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //request : http://localhost/api/server/hello
    //response

    public UserResponse hello() {

        URI uri = UriComponentsBuilder //주소를 만들 경우 사용
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "aaa")
                .queryParam("age", 99)
                .encode() //parameter가 붙을 경우에 안정적으로 encoding 해서 보내야 하기 때문에
                .build()
                .toUri();

        //http://localhost:9090/api/server/hello?name=steve&age=10 queryParam를 사용하면 주소뒤에 queryparameter가 붙음

        System.out.println(uri.toString());// 위의 주소가 잘 만들어 졌는지 확인

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate을 통해서 통신을 한다.
        //String result = restTemplate.getForObject(uri, String.class); //uri에 요청을 시키고 문자열로 받는다(response로 받을 형태(String) 지정).
        //getForObject가 실행되는 순간이 client에서 http 서버로 붙는 순간임.
        //getForObject는 http의 메서드이다.

        //ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class); //response로 ResponseEntity, type은 string으로 받음

        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class); //UserResponse를 받을 경우, 괄호 안에 받고자 하는 타입 명시

        System.out.println(result.getStatusCode()); //http의 statuscode 보여줌
        System.out.println(result.getBody()); //response 내용의 body 보여줌

        return result.getBody(); //result의 getBody()를 해야 정확한 내용을 볼 수 있음.
                                 //UserResponse를 받을 경우, getBody를 하게 되면 body안에 UserResponse가 들어있기 때문에 return이 String이 아니라
                                 //UserResponse로 바뀜.
    }
}
