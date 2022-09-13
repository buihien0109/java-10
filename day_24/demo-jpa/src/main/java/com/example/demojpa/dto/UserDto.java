package com.example.demojpa.dto;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.demojpa.entity.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
}