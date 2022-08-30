package com.mehmetkicirti.blogapplication.dto.user;


import com.mehmetkicirti.blogapplication.utility.validator.password.PasswordValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @Email
    @Length(min = 5, max = 125)
    private String  email;
    @PasswordValidator
    private String password;
}
