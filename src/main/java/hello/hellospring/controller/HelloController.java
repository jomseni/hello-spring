package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")  //hello가 들어오면 이 아래의 메서드를 실행해준다
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }
}
