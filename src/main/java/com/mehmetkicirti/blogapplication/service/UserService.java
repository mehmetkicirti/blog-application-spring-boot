package com.mehmetkicirti.blogapplication.service;


import com.mehmetkicirti.blogapplication.dto.user.RegisterUserDto;
import com.mehmetkicirti.blogapplication.dto.user.UpdateUserDto;
import com.mehmetkicirti.blogapplication.entity.concrete.User;
import com.mehmetkicirti.blogapplication.utility.wrapper.UserResponse;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.Response;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.ResponseData;

public interface UserService{
    ResponseData<UserResponse> register(RegisterUserDto request);
    ResponseData<UserResponse> updateUser(UpdateUserDto request, String userId);
    Response deleteUser(String id);
    User findUserById(String id);
}
