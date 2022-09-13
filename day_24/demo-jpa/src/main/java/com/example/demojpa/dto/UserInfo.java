package com.example.demojpa.dto;

/**
 * A Projection for the {@link com.example.demojpa.entity.User} entity
 */
public interface UserInfo {
    Integer getId();

    String getName();

    String getEmail();
}