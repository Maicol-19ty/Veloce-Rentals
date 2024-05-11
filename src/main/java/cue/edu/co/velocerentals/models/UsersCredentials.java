package cue.edu.co.velocerentals.models;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@SessionScoped

public class UsersCredentials implements Serializable {

    private String username;
    private String password;
    private String roleName;

}
