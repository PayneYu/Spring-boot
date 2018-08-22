package com.study.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.user.entiry.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Long deleteById(Long id);
}