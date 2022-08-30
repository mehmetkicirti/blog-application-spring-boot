package com.mehmetkicirti.blogapplication.dto.user;

import com.mehmetkicirti.blogapplication.dto.BaseDto;
import com.mehmetkicirti.blogapplication.utility.constant.ValidatorMessageConstants;
import com.mehmetkicirti.blogapplication.utility.validator.password.PasswordValidator;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RegisterUserDto implements BaseDto {
    @NotEmpty(message = ValidatorMessageConstants.NOT_EMPTY_FIRST_NAME)
    private String firstname;
    @NotEmpty(message = ValidatorMessageConstants.NOT_EMPTY_FIRST_NAME)
    private String lastname;
    @NotEmpty(message = ValidatorMessageConstants.NOT_EMPTY_USERNAME)
    private String username;
    @NotEmpty(message = ValidatorMessageConstants.NOT_EMPTY_EMAIL)
    @Email
    private String email;
    @NotEmpty(message = ValidatorMessageConstants.NOT_EMPTY_PASSWORD)
    @PasswordValidator
    private String password;
}
