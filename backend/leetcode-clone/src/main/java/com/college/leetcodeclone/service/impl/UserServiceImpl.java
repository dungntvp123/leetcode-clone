package com.college.leetcodeclone.service.impl;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.common.ResponseStatus;
import com.college.leetcodeclone.data.dto.request.UserSearchingCriteriaRequestDto;
import com.college.leetcodeclone.data.dto.response.LoadDataResponseDto;
import com.college.leetcodeclone.data.dto.response.UserInfoResponseDto;
import com.college.leetcodeclone.data.entity.Account;
import com.college.leetcodeclone.data.entity.User;
import com.college.leetcodeclone.repository.AccountRepository;
import com.college.leetcodeclone.repository.UserRepository;
import com.college.leetcodeclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public ResponseBody<?> loadUser(UserSearchingCriteriaRequestDto criteria) {
        criteria = criteria.formatData();
        Specification<User> spec = nameLike(criteria.getName())
                .and(emailLike(criteria.getEmail()));
        Pageable pageable = PageRequest.of(criteria.getPageIndex(),
                criteria.getPageSize(),
                criteria.getSortElement().getOrder() == "desc" ?
                        Sort.by(criteria.getSortElement().getElement()).descending() :
                Sort.by(criteria.getSortElement().getElement()));

        Page<User> users = userRepository.findAll(spec, pageable);
        Page<UserInfoResponseDto> responseDto = users.map(UserInfoResponseDto::new);

        return new ResponseBody<>(ResponseStatus.DATA_LOADED_SUCCESSFUL, new LoadDataResponseDto<>(responseDto));
    }

    @Override
    public ResponseBody<?> loadUser(String username) {
        User user = accountRepository.findByUsername(username).get().getUser();
        return new ResponseBody<>(ResponseStatus.DATA_LOADED_SUCCESSFUL,
                new LoadDataResponseDto<>(new UserInfoResponseDto(user)));
    }
}
