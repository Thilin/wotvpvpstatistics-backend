package com.mxh.wotvpvpstatisticsbackend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
    @Schema(description = "User name", example = "Fernando Cauper")
    private String name;
    @Schema(description = "User nickName", example = "Thilin")
    private String nickName;
    @Schema(description = "User email", example = "email@email.com")
    private String email;
    @Schema(description = "User password", example = "123")
    private String password;
    @Schema(description = "User password confirmation", example = "123")
    private String passwordConfirmation;
}
