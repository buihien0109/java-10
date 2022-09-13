package com.example.todolistbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "status")
    private Boolean status;

    @PrePersist
    public void prePersist() {
        if(status == null) {
            status = false;
        }
    }

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, Boolean status) {
        this.title = title;
        this.status = status;
    }

    // Nếu 1 trường mang giá trị mặc định có thể set như sau
    // C1 : private Boolean status = false;
    // C2 : columnDefinition
    // C3 : sử dụng lifecycle


}
