package org.example.pdfstudentiv2.repositories;

import org.example.pdfstudentiv2.Role;
import org.example.pdfstudentiv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByRole(Role role);
}
