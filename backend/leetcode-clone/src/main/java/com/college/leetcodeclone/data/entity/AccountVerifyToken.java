package com.college.leetcodeclone.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountVerifyToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Timestamp expiration;
    @OneToOne(mappedBy = "accountVerifyToken", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Account account;

    public boolean isExpired() {
        return new Timestamp(System.currentTimeMillis()).after(expiration);
    }
}
