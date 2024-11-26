package org.example.pdfstudentiv2.services;

import org.example.pdfstudentiv2.entities.PDFFile;
import org.example.pdfstudentiv2.entities.User;
import org.example.pdfstudentiv2.repositories.PDFFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PDFService {

    private static final String ROOT_DIRECTORY = "pdfs";
    private final PDFFileRepository pdfFileRepository;

    public PDFService(PDFFileRepository pdfFileRepository) {
        this.pdfFileRepository = pdfFileRepository;
    }

    // Save PDF to user's directory
    public void savePDF(MultipartFile file, User user) throws IOException {
        // Define the user's directory
        File userDir = new File(ROOT_DIRECTORY, user.getUsername());

        // Create the directory if it does not exist
        if (!userDir.exists() && !userDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + userDir.getAbsolutePath());
        }

        // Save the file in the user's directory
        File destination = new File(userDir, file.getOriginalFilename());
        System.out.println("User Directory: " + userDir.getAbsolutePath());
        System.out.println("Destination File: " + destination.getAbsolutePath());
        file.transferTo(destination.toPath());

        // Optionally save metadata to the database
        PDFFile pdfFile = new PDFFile();
        pdfFile.setFilename(file.getOriginalFilename());
        pdfFile.setFilepath(destination.getAbsolutePath());
        pdfFile.setUser(user);

        pdfFileRepository.save(pdfFile);
    }

    // Get PDFs for a specific user
    public List<PDFFile> getUserPDFs(User user) {
        return pdfFileRepository.findByUser(user);
    }
}
