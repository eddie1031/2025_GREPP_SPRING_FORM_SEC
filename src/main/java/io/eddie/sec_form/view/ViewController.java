package io.eddie.sec_form.view;

import io.eddie.sec_form.app.MemberService;
import io.eddie.sec_form.dto.SignUpForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String showSignupForm() {
        return "sign_up";
    }

    @PostMapping("/signup")
    public String doSignUp(SignUpForm signUpForm) {
        memberService.save(signUpForm);
        return "redirect:/";
    }

    @GetMapping("/signin")
    public String showSignInForm() {
        return "sign_in";
    }


}
