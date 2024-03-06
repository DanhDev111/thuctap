package com.example.thuctap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User extends TimeAuditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(unique = true)
    private String username;

    private String password;

    @NaturalId
    @Email
    @Column(unique = true)
    private String email;

    private String gender; // bạn lưu String mà bạn định dang enum thì nó chả lấy ra enum luu cho ban

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public User(String name, String username, String password, String email, String gender) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }
}
