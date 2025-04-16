package io.eddie.sec_form.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

    private String username;
    private String password;
    private String email;

}

