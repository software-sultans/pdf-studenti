package org.example.pdfstudentiv2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.pdfstudentiv2.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PDFFile> pdfFiles = new ArrayList<>();

    // Add getters and setters for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Add getter and setter for pdfFiles
    public List<PDFFile> getPdfFiles() {
        return pdfFiles;
    }

    public void setPdfFiles(List<PDFFile> pdfFiles) {
        this.pdfFiles = pdfFiles;
    }
}