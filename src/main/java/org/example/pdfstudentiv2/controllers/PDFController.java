package org.example.pdfstudentiv2.controllers;

import org.example.pdfstudentiv2.entities.PDFFile;
import org.example.pdfstudentiv2.entities.User;
import org.example.pdfstudentiv2.repositories.UserRepository;
import org.example.pdfstudentiv2.services.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/upload")
    public String uploadPage(Model model, Authentication authentication) {
        // Get logged-in user
        User user = userRepository.findByUsername(authentication.getName());
        List<PDFFile> userPDFs = pdfService.getUserPDFs(user);

        // Pass user's PDFs to the view
        model.addAttribute("pdfs", userPDFs);
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadPDF(@RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
        // Get logged-in user
        User user = userRepository.findByUsername(authentication.getName());

        // Save the file
        pdfService.savePDF(file, user);

        return "redirect:/upload";
    }
}
