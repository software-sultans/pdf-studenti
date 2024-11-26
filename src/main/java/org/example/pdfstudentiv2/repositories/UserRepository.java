package org.example.pdfstudentiv2.repositories;

import org.example.pdfstudentiv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
