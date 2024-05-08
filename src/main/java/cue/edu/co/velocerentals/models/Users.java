package cue.edu.co.velocerentals.models;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@SessionScoped

public class Users implements Serializable {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String full_name;
    private LocalDateTime created_at;
    private LocalDateTime last_login;
}

