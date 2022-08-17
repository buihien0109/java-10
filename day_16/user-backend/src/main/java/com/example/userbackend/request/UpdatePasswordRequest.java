package com.example.userbackend.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {
    @NotEmpty(message = "Old password is required")
    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @NotEmpty(message = "New password is required")
    @NotBlank(message = "New password is required")
    @Length(min = 4, message = "Độ dài ít nhất 4 ký tự")
    private String newPassword;
}
