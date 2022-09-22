package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")  //hello가 들어오면 이 아래의 메서드를 실행해준다
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }
    @GetMapping("hello-mvc") //url 주소에 치는 부분이라고 생각해도 된다 ex) localhost:8080/hello-mvc
    public String helloMvc(@RequestParam ("name") String name, Model model){   //RequestParam을 통해 웹에서 파라미터를 받아서 적용한다!(동적)
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody  // http의 body부에 return 값의 데이터를 직접 넣겠다는 뜻이다!
    public String helloString(@RequestParam("name")String name){
        return "hello " + name; //만약  위의 String 파라미터에 sprting 을 넣는다면 hello spring 이 나올 것이다!!
    }

//    데이터를 내놔! 할 때 사용한다!
    @GetMapping("hello-api")
    @ResponseBody


    //json 방식 : 출력결과
    //최근엔 다 json 방식 사용한다
   public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 return , 디폴트 값이 기본적으로 json방식으로 리턴한다!
    }

    static class Hello{
        private String name;

        public String getName(){                //꺼낼때는 getname

            return name;
        }

        public void setName(String name){       //넣을때는 setname
            this.name = name;
        }
    }
}
