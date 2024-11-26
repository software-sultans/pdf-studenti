package org.example.pdfstudentiv2.repositories;

import org.example.pdfstudentiv2.entities.PDFFile;
import org.example.pdfstudentiv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PDFFileRepository extends JpaRepository<PDFFile, Long> {
    List<PDFFile> findByUser(User user);
}
