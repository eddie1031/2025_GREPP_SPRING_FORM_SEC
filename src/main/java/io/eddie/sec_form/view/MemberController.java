package io.eddie.sec_form.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/signup")
    public String showSignupForm() {
        return "sign_up";
    }

    @GetMapping("/signin")
    public String showSignInForm() {
        return "sign_in";
    }


}
