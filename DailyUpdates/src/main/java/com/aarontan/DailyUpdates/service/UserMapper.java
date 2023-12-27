package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.models.User;
import com.aarontan.DailyUpdates.payload.response.UserDTO;
public class UserMapper {
    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
