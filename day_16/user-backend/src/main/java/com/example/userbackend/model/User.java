package com.example.userbackend.model;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String password;
}
