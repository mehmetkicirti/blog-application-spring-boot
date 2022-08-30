package com.mehmetkicirti.blogapplication.utility.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String firstname;
    private String lastname;
    private String userName;
    private String email;
}
