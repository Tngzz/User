package org.aelion.Users.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserResponse {
    private String id;
    private String name;
    private Community community;
}
