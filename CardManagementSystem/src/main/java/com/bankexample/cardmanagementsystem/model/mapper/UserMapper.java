package com.bankexample.cardmanagementsystem.model.mapper;


import com.bankexample.cardmanagementsystem.model.dto.request.UserCreateRequest;
import com.bankexample.cardmanagementsystem.model.dto.request.UserUpdateRequest;
import com.bankexample.cardmanagementsystem.model.dto.response.UserResponse;
import com.bankexample.cardmanagementsystem.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(target = "userFullName", expression = "java(user.getLastName() + \" \" + user.getFirstName() + \" \" + user.getMiddleName())")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "registrationDate", target = "registrationDate")
    UserResponse fromUser(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "registrationDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    User toCreateRequest(UserCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UserUpdateRequest request, @MappingTarget User user);
}