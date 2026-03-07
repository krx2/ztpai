package pl.edu.pk.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    // 1. GET /hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    // 2. GET /hello/{name}
    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    // 3. GET /greet?name=X
    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    // 4. GET /info
    @GetMapping("/info")
    public AppInfo info() {
        return new AppInfo("Konrad Żebro", "Spring Boot", "0.0.1-SNAPSHOT");
    }

    record AppInfo(String autor, String framework, String wersja) {}
}