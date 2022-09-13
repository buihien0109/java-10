package com.example.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "identity_card")
public class IdentityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "expried")
    private LocalDate expried; // ngày cấp

    @Column(name = "issued")
    private LocalDate issued; // ngày hết hạn

    @PrePersist
    public void prePersist() {
        expried = LocalDate.now().minusYears(2);
        issued = LocalDate.now().plusYears(2);
    }
}