package com.college.leetcodeclone.data.metamodel;

import com.college.leetcodeclone.data.entity.Account;
import com.college.leetcodeclone.data.entity.Image;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.apache.catalina.User;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Image> image;
    public static volatile SingularAttribute<User, Account> account;
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String ACCOUNT = "account";
    public static final String IMAGE = "image";
}
