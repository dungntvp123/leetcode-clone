package com.college.leetcodeclone.schedule;

import com.college.leetcodeclone.data.entity.Account;
import com.college.leetcodeclone.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DataCleanerSchedule {
    @Autowired
    private AccountRepository accountRepository;

    @Scheduled(fixedDelay = 5 * 60 * 1000l)
    @Transactional
    public void cleanDisableAccount() {
        log.info("start cleaner");
        List<Account> disables = accountRepository.findByIsEnable(false);
        log.info("found {} trash data", disables.size());
        accountRepository.deleteAll(disables);
    }
}
