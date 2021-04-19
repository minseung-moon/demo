package hello.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //MVC 방식
    // WebApplication에서 /hello로 접근을 하면 아래에 밑에 메소드를 호출
    // Get은 get, post 방식 중 get 방식으로 url로 접근했을 때 호출
    @GetMapping("hello")
    public String hello(Model model) {
        // key,value로 설정
        model.addAttribute("data","spring!!");
        // template 중 hello라는 이름을 가진 template에 전달, 여기서는 hello.html
        // resources:templates/ +{ViewName}+ .html, viewname은 return 값
        return "hello";
    }

    @GetMapping("hello-mvc")
    // 기본적으로 required가 true이기 때문에 값을 넣어줘야 한다
    // value = "name", required = false로 하면 값을 넘겨주지 않아도 된다
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    @GetMapping("hello-string")
    // ResponseBody : http에서 head부분과 body부분이 있는데, 그 body부분에 데이터를 직접 넣어 주겠다!
    // view가 없이 그대로 내려간다
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

    // JSON 방식으로 객체 전달(key, value)
    // xml은 무겁고 열고 닫고 태그를 두번써야 하는거에 비해 JSON은 객체 형식으로 심플하다
    // 객체로 return하게 되면 ViewEesolve 대신에 HttpMessageConverter가 동작하면서 JsonConverter랑 StringConverter 중 JSON으로 자동 변환해서 전송한다
    // 기본문자처리 : StringHttpMessageConverter
    // 기본 객체처리 : MappingJackson2HttpMessageConverter
    // byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // getter, setter, property 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
