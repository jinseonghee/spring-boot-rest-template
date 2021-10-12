package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    //https://openapi.naver.com/v1/search/local.json
    // ?query=%EC%A3%BC%EC%8B%9D
    // &display=10
    // &start=1
    // &sort=random

    @GetMapping("/naver")
    public String naver() {

        String query = "갈비집";
        //String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));


        URI uri = UriComponentsBuilder//주소 만드는 uriBuilder
                .fromUriString("https://openapi.naver.com")
                .path("v1/search/local.json")
                .queryParam("query", "갈비집")
                .queryParam("display", "10")
                .queryParam("start", "1")
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity //RequestEntity는 header를 사용하기 위해 사용 , Get으로 넣을 거니까 들어갈게 없어서 void를 해줌
                .get(uri) //get방식에 uri를 넣을 것이라는 뜻
                .header("X-Naver-Client-Id", "9xDkgqPP5wx72xCn_og0")
                .header("X-Naver-Client-Secret", " kuj400PhD7")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age) { //api Controller에서 요청이 들어오면 여기서 response를 내려줌
                                                                  //Client의 Queryparameter를 넣어준걸 여기서 @RequestParam으로 받아줌.

        User user = new User();
        //user.setName("steve");
        user.setName(name); //Requestparameter를 받을 경우 echo로 동작
        user.setAge(age);
        //user.setAge(10);
        return user; //그냥 Object로 return 시킴
        //return "hello server";
    }

    @PostMapping("/user/{userId}/name/{userName}") //{userId}, {userName}을 @PathVariable를 이용해 변수로 매칭
    public Req<User> post(
                     //HttpEntity<String> entity, //순수한 HttpEntity가 들어옴(client가 뭘 보냈는지 모를경우 getBody를 찍어보기 위해 사용)
                     @RequestBody Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customHeader
    ) {
        //log.info("req : {}", entity.getBody());
        log.info("userId : {}, userName : {}" , userId, userName);
        log.info("authorization : {}, custom : {}" , authorization, customHeader);
        log.info("client req : {}", user);

        //response 할 때도 똑같이 맞춰서 보내주면 된다.
        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header() //header에 빈값 아무거나 넣음
        );
        response.setResBody(
                user.getResBody()
        );

        return response;
    }
}
