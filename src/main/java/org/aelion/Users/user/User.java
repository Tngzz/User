package org.aelion.Users.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter

public class User {
    @Id
    private String id;

    private String name;

    private String community_id;

    private String roleId;

}
