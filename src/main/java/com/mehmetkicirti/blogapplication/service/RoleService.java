package com.mehmetkicirti.blogapplication.service;

import com.mehmetkicirti.blogapplication.dto.role.AddRoleDto;
import com.mehmetkicirti.blogapplication.dto.role.RoleDto;
import com.mehmetkicirti.blogapplication.dto.role.UpdateRoleDto;
import com.mehmetkicirti.blogapplication.entity.concrete.Role;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.Response;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.ResponseData;

import java.util.List;

public interface RoleService {
    Response addRole(AddRoleDto request);
    Response updateRole(UpdateRoleDto request, String roleId);
    Role findByName(String roleName);
    ResponseData<RoleDto> getRoleById(String id);
    ResponseData<List<RoleDto>> getRoles();
    Response delete(String id);
}
