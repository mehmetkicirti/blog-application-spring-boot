package com.mehmetkicirti.blogapplication.service.impl;

import com.mehmetkicirti.blogapplication.dto.user.RegisterUserDto;
import com.mehmetkicirti.blogapplication.dto.user.UpdateUserDto;
import com.mehmetkicirti.blogapplication.entity.concrete.Role;
import com.mehmetkicirti.blogapplication.entity.concrete.User;
import com.mehmetkicirti.blogapplication.repository.UserRepository;
import com.mehmetkicirti.blogapplication.service.RoleService;
import com.mehmetkicirti.blogapplication.service.UserService;
import com.mehmetkicirti.blogapplication.utility.ServiceRules;
import com.mehmetkicirti.blogapplication.utility.constant.MessageConstants;
import com.mehmetkicirti.blogapplication.utility.constant.ValidatorMessageConstants;
import com.mehmetkicirti.blogapplication.utility.exception.BlogAPIException;
import com.mehmetkicirti.blogapplication.utility.exception.ResourceNotFoundException;
import com.mehmetkicirti.blogapplication.utility.wrapper.UserResponse;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.Response;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.ResponseData;
import com.mehmetkicirti.blogapplication.utility.wrapper.concretes.ErrorResponseImpl;
import com.mehmetkicirti.blogapplication.utility.wrapper.concretes.ResponseDataImpl;
import com.mehmetkicirti.blogapplication.utility.wrapper.concretes.ResponseImpl;
import com.mehmetkicirti.blogapplication.utility.wrapper.concretes.SuccessResponseImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseData<UserResponse> register(RegisterUserDto request) {
        Response logic = ServiceRules.runLogics(Arrays.asList(isExistEmail(request.getEmail()),
                isExistUserName(request.getUsername())));
        if(logic != null && !logic.getSuccess()) {
            ErrorResponseImpl errorResponse = (ErrorResponseImpl) logic;
            throw new BlogAPIException(errorResponse.getStatusCode(),errorResponse.getMessage());
        }
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleService.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        return new ResponseDataImpl<>(
                MessageConstants.USER_REGISTERED_SUCCESSFULLY,
                modelMapper.map(user, UserResponse.class)
        );
    }

    @Override
    public ResponseData<UserResponse> updateUser(UpdateUserDto request, String userId) {
        User currentUser = findUserById(userId);

        if(StringUtils.hasText(request.getEmail()) && StringUtils.hasText(request.getUsername()) &&
                (!request.getEmail().equals(currentUser.getEmail()) ||
                !request.getUsername().equals(currentUser.getUsername()))){

            Response logic = ServiceRules.runLogics(Arrays.asList(isExistEmail(request.getEmail()),
                    isExistUserName(request.getUsername())));

            if(logic != null && !logic.getSuccess()){
                ErrorResponseImpl errorResponse = (ErrorResponseImpl) logic;
                throw new BlogAPIException(errorResponse.getStatusCode(),errorResponse.getMessage());
            }
            currentUser.setEmail(request.getEmail());
            currentUser.setUsername(request.getUsername());
        }

        if(StringUtils.hasText(request.getPassword())){
            currentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if(StringUtils.hasText(request.getFirstname())){
            currentUser.setFirstname(request.getFirstname());
        }

        if(StringUtils.hasText(request.getLastname())){
            currentUser.setLastname(request.getLastname());
        }

        User updatedUser = userRepository.save(currentUser);
        return new ResponseDataImpl<>(
             MessageConstants.USER_UPDATED_SUCCESSFULLY,
                modelMapper.map(updatedUser, UserResponse.class)
        );
    }

    @Override
    public Response deleteUser(String id) {
        User user = findUserById(id);
        userRepository.delete(user);
        return new SuccessResponseImpl(MessageConstants.USER_DELETED_SUCCESSFULLY);
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
    }

    private Response isExistEmail(String email) {
        if(userRepository.existsByEmail(email))
            return new ErrorResponseImpl(HttpStatus.BAD_REQUEST, ValidatorMessageConstants.EMAIL_ALREADY_EXISTS);
        return new ResponseImpl(true);
    }

    private Response isExistUserName(String username) {
        if(userRepository.existsByUsername(username))
            return new ErrorResponseImpl(HttpStatus.BAD_REQUEST, ValidatorMessageConstants.USERNAME_ALREADY_EXISTS);
        return new ResponseImpl(true);
    }
}
