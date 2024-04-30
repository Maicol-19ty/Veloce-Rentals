package cue.edu.co.velocerentals.models;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Users {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String full_name;
    private LocalDateTime created_at;
    private LocalDateTime last_login;
}

