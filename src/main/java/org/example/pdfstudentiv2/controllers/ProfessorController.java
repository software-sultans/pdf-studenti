package org.example.pdfstudentiv2.controllers;

import jakarta.annotation.Resource;
import org.example.pdfstudentiv2.Role;
import org.example.pdfstudentiv2.entities.PDFFile;
import org.example.pdfstudentiv2.entities.User;
import org.example.pdfstudentiv2.repositories.PDFFileRepository;
import org.example.pdfstudentiv2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private UserRepository userRepository; // Repository pentru lista de studenți
    @GetMapping("/dashboard")
    public String professorDashboard(Model model) {
        List<User> students = userRepository.findByRole(Role.STUDENT);
        model.addAttribute("students", students);

        return "dashboards/professor-dashboard";
    }
    @Autowired
    private PDFFileRepository pdfFileRepository;

    @GetMapping("/all-pdfs")
    public String getAllStudentPdfs(Model model) {
        List<PDFFile> pdfs = pdfFileRepository.findAll();
        model.addAttribute("pdfs", pdfs);
        return "pdf-list";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadPdf(@PathVariable Long id) {
        try {
            Optional<PDFFile> pdfFile = pdfFileRepository.findById(id);
            if (pdfFile.isPresent()) {
                File file = new File(pdfFile.get().getFilepath());
                if (!file.exists()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("File not found on disk: " + pdfFile.get().getFilepath());
                }

                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFile.get().getFilename() + "\"")
                        .contentLength(file.length())
                        .body(resource);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/search-students")
    @ResponseBody
    public List<String> searchStudents(@RequestParam String term){
        List<User> matchingStudents = userRepository.findByRole(Role.STUDENT).stream()
                .filter(student -> student.getUsername().toLowerCase().contains(term.toLowerCase()))
                .collect(Collectors.toList());
        return matchingStudents.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
}