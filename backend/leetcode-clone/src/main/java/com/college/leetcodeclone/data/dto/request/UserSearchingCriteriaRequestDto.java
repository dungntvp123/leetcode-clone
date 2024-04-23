package com.college.leetcodeclone.data.dto.request;

import com.college.leetcodeclone.common.SortElement;
import com.college.leetcodeclone.data.metamodel.User_;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchingCriteriaRequestDto {
    private String email;
    private String name;
    private SortElement sortElement;
    private Integer pageSize;
    private Integer pageIndex;

    public UserSearchingCriteriaRequestDto formatData() {
        email = email == null ? "" : email;
        name = name == null ? "" : name;
        sortElement = sortElement == null ? new SortElement(User_.NAME, "asc") : sortElement;
        pageIndex = pageIndex == null ? 0 : pageIndex - 1;
        pageSize = pageSize == null ? 10 : pageSize;
        return this;
    }
}
