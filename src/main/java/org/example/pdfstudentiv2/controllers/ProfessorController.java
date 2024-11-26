package org.example.pdfstudentiv2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @GetMapping("/dashboard")
    public String professorDashboard() {
        return "dashboards/professor-dashboard";
    }
}