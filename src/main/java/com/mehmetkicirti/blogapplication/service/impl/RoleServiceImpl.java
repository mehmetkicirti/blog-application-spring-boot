package com.mehmetkicirti.blogapplication.service.impl;

import com.mehmetkicirti.blogapplication.dto.role.AddRoleDto;
import com.mehmetkicirti.blogapplication.dto.role.RoleDto;
import com.mehmetkicirti.blogapplication.dto.role.UpdateRoleDto;
import com.mehmetkicirti.blogapplication.entity.concrete.Role;
import com.mehmetkicirti.blogapplication.repository.RoleRepository;
import com.mehmetkicirti.blogapplication.service.RoleService;
import com.mehmetkicirti.blogapplication.utility.constant.MessageConstants;
import com.mehmetkicirti.blogapplication.utility.constant.ValidatorMessageConstants;
import com.mehmetkicirti.blogapplication.utility.exception.ResourceNotFoundException;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.Response;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.ResponseData;
import com.mehmetkicirti.blogapplication.utility.wrapper.concretes.ErrorResponseImpl;
import com.mehmetkicirti.blogapplication.utility.wrapper.concretes.ResponseDataImpl;
import com.mehmetkicirti.blogapplication.utility.wrapper.concretes.SuccessResponseImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(ModelMapper modelMapper,
                           RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public Response addRole(AddRoleDto request) {
        Role role = modelMapper.map(request, Role.class);
        roleRepository.save(role);
        return new SuccessResponseImpl(MessageConstants.ROLE_SAVED_SUCCESSFULLY);
    }

    @Override
    public Response updateRole(UpdateRoleDto request, String roleId) {
        RoleDto roleDto = getRoleById(roleId).getData();

        if(!request.getName().equals(roleDto.getName())){
            return new ErrorResponseImpl(HttpStatus.BAD_REQUEST, ValidatorMessageConstants.ROLE_NAME_SAME);
        }

        roleRepository.save(modelMapper.map(roleDto, Role.class));
        return new SuccessResponseImpl(MessageConstants.ROLE_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(()-> new ResourceNotFoundException("Role", "roleName", roleName));
    }

    @Override
    public ResponseData<RoleDto> getRoleById(String id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Role", "id", id));
        return new ResponseDataImpl<>(modelMapper.map(role, RoleDto.class));
    }

    @Override
    public ResponseData<List<RoleDto>> getRoles() {
       List<RoleDto> roles = roleRepository.findAll()
                .stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());

        return new ResponseDataImpl<>(roles);
    }

    @Override
    public Response delete(String id) {
        Role role = modelMapper.map(getRoleById(id).getData(), Role.class);
        roleRepository.delete(role);
        return new SuccessResponseImpl(MessageConstants.ROLE_DELETED_SUCCESSFULLY);
    }
}
