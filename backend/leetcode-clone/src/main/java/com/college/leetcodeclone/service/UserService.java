package com.college.leetcodeclone.service;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.data.dto.request.UserSearchingCriteriaRequestDto;
import com.college.leetcodeclone.specification.UserSpecification;

public interface UserService extends UserSpecification {
    ResponseBody<?> loadUser(UserSearchingCriteriaRequestDto criteria);

    ResponseBody<?> loadUser(String username);
}
