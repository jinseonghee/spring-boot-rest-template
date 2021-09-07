package com.example.client.controller;

import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    @Autowired //옛날에 쓰던 주입 방식. 지금은 밑에 처럼 생성자 주입 방식 사용
    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public UserResponse gethello() {
        return restTemplateService.hello(); //controller로 get으로 요청이 들어오면 restTemplateService 통해서 server로 호출해서
                                            //응답을 받아서 response를 내림
    }
}
