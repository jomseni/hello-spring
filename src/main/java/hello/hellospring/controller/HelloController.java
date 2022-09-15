package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")  //hello가 들어오면 이 아래의 메서드를 실행해준다
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }
    @GetMapping("hello-mvc") //url 주소에 치는 부분이라고 생각해도 된다 ex) localhost:8080/hello-mvc
    public String helloMvc(@RequestParam ("name") String name, Model model){   //RequestParam을 통해 웹에서 파라미터를 받아서 적용한다!
        model.addAttribute("name", name);
        return "hello-template";
    }
}
