package com.example.demojpa.repository;

import com.example.demojpa.dto.UserDto;
import com.example.demojpa.dto.UserInfo;
import com.example.demojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 1. Query method
    List<User> findByNameContainingIgnoreCase(String name);

    List<User> getByAgeGreaterThanOrderByAgeDesc(Integer age);

    boolean existsByEmailIgnoreCase(String email);

    void deleteByEmailIgnoreCase(String email);

    User getUserById(Integer id);

    // 2. JPA Query Language
    @Query("select u from User u where u.id = ?1")
    User test(Integer id);

    @Query("select u from User u where u.id = :id")
    User test1(@Param("id") Integer id);

    // 3. Native query
    @Query(nativeQuery = true, value = "select * from user u where u.id = ?1")
    User test2(Integer id);

    // 2. JPA Query Language -> Dto
    @Query("select new com.example.demojpa.dto.UserDto(u.id, u.name, u.email) from User u where u.id = ?1")
    UserDto getUserDtoById(Integer id);

    // 2. JPA Query Language -> Projection
    UserInfo getUserInfoById(Integer id);

    @Query(nativeQuery = true, name = "getUserByIdUsingNativeQuery")
    UserDto getUserByIdUsingNativeQuery(Integer id);

    // Sử dụng EntityManager
    // Sử dụng Custom Repository

    @Modifying
    @Transactional
    @Query("update User u set u.name = ?2 where u.id = ?1")
    void updateUser(Integer id, String name);
}