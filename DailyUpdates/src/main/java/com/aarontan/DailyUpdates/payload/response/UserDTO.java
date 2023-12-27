package com.aarontan.DailyUpdates.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String email;
    private String username;
}
