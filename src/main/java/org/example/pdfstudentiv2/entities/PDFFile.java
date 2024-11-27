package org.example.pdfstudentiv2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pdfs")
public class PDFFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String filename;

    @Getter
    @Setter
    private String filepath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Setter
    @Getter
    private User user;

    // Add getters and setters for id if needed
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}