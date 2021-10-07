package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

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
