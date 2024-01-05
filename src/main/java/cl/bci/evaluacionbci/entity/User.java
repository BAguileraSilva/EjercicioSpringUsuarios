package cl.bci.evaluacionbci.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Size(max = 255)
    @Column(name = "ID_USER", nullable = false)
    private String idUser;

    @Column(name = "CREATED")
    private LocalDateTime created;

    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "MODIFIED")
    private LocalDateTime modified;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Size(max = 255)
    @Column(name = "NAME")
    private String name;

    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;

    @Size(max = 2000)
    @Column(name = "TOKEN", length = 2000)
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

}