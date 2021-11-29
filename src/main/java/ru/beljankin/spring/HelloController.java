package ru.beljankin.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello_world"; // метод возвращает представление, а они лежат в папке scr/webapp/WEB-INF/views
    }
}
