package com.example.server.controller;

import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
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
    public User post(@RequestBody User user, @PathVariable int userId, @PathVariable String userName) {
        log.info("userId : {}, userName : {}" , userId, userName);
        log.info("client req : {}", user);

        return user;
    }
}
