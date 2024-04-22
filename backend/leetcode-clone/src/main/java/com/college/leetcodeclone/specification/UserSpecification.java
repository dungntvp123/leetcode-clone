package com.college.leetcodeclone.specification;

import com.college.leetcodeclone.data.entity.Account;
import com.college.leetcodeclone.data.entity.User;
import com.college.leetcodeclone.data.metamodel.Account_;
import com.college.leetcodeclone.data.metamodel.User_;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public interface UserSpecification {
    default Specification<User> nameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(User_.NAME), "%"+name+"%");
    }
    default Specification<User> emailLike(String email) {
        return (root, query, criteriaBuilder) -> {
            Join<Account, User> userJoin = root.join(User_.ACCOUNT);
            return criteriaBuilder.like(userJoin.get(Account_.EMAIL), "%"+email+"%");
        };
    }
}
