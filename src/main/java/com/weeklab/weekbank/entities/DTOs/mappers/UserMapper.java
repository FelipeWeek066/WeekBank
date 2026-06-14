package com.weeklab.weekbank.entities.DTOs.mappers;

import com.weeklab.weekbank.entities.DTOs.UserDTO;
import com.weeklab.weekbank.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    List<UserDTO> usersToUserDtos(List<User> users);
}
