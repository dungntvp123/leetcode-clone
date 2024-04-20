package com.college.leetcodeclone.repository;

import com.college.leetcodeclone.data.entity.AccountVerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountVerifyTokenRepository extends JpaRepository<AccountVerifyToken, Integer> {
    Optional<AccountVerifyToken> findByDescription(String verifyToken);
}
