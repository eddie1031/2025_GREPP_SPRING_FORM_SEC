package io.eddie.sec_form.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/user")
    public String users() {
        return "User!";
    }

    @GetMapping("/manager")
    public String manager() {
        return "Manager!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin!";
    }

}
