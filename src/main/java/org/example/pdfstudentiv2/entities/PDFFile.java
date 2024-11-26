package org.example.pdfstudentiv2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class PDFFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String filename;

    @Getter
    @Setter
    private String filepath; // Path where the file is stored

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    @Getter
    private User user;

    // Getters and setters omitted for brevity
}
