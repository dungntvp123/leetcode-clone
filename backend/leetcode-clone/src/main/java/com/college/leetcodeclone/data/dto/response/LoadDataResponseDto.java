package com.college.leetcodeclone.data.dto.response;

import com.college.leetcodeclone.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDataResponseDto <T> {
    private T data;
}
