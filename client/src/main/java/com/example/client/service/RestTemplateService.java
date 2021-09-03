package com.example.client.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //request : http://localhost/api/server/hello
    //response

    public String hello() {

        URI uri = UriComponentsBuilder //주소를 만들 경우 사용
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .encode() //parameter가 붙을 경우에 안정적으로 encoding 해서 보내야 하기 때문에
                .build()
                .toUri();

        System.out.println(uri.toString());// 위의 주소가 잘 만들어 졌는지 확인

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate을 통해서 통신을 한다.
        //String result = restTemplate.getForObject(uri, String.class); //uri에 요청을 시키고 문자열로 받는다(response로 받을 형태(String) 지정).
        //getForObject가 실행되는 순간이 client에서 http 서버로 붙는 순간임.
        //getForObject는 http의 메서드이다.

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class); //response로 ResponseEntity, type은 string으로 받음

        System.out.println(result.getStatusCode()); //http의 statuscode 보여줌
        System.out.println(result.getBody()); //response 내용의 body 보여줌

    }
}
