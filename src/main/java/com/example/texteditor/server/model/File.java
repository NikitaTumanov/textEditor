package com.example.texteditor.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Data
@Table(name = "textFiles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    String name;

    @Column(name = "password", length = 200, nullable = false)
    String password;

    @Column(name = "file", length = 200, nullable = false)
    String file;

    @Column(name = "user_id", length = 200, nullable = false)
    private Long userId;

}
