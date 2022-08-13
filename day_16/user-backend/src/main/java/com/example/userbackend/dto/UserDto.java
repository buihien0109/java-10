package com.example.userbackend.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
}
