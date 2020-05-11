package com.home.employeemanagement.repositories;

import com.home.employeemanagement.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount , Long> {
}
