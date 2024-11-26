package org.example.pdfstudentiv2.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/dashboard")
    public String clientDashboard() {
        return "dashboards/student-dashboard";
    }
}