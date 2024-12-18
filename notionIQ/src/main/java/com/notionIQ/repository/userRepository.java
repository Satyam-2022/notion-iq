package com.notionIQ.repository;

import com.notionIQ.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
