package org.example.pdfstudentiv2.repositories;

import org.example.pdfstudentiv2.entities.PDFFile;
import org.example.pdfstudentiv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PDFRepository extends JpaRepository<PDFFile, Long> {
    List<PDFFile> findByUser(User user);
}
