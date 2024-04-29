package cue.edu.co.velocerentals.mapping.DTO;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UsersDTo(Integer user_id, String username, String password, String email, String full_name, LocalDateTime created_at, LocalDateTime last_login) {
}
