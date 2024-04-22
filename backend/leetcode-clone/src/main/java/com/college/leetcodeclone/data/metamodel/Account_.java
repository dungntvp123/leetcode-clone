package com.college.leetcodeclone.data.metamodel;

import com.college.leetcodeclone.data.entity.Account;
import com.college.leetcodeclone.data.entity.User;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Account.class)
public class Account_ {
    public static volatile SingularAttribute<Account, String> email;
    public static volatile SingularAttribute<Account, User> user;
    public static volatile SingularAttribute<Account, Boolean> isEnable;
    public static final String EMAIL = "email";
    public static final String USER = "user";
    public static final String IS_ENABLE = "isEnable";
}
