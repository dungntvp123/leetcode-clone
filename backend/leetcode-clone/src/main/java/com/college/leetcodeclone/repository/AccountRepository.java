package com.college.leetcodeclone.repository;

import com.college.leetcodeclone.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByUsername(String username);
}
