package com.example.demo.dto.column;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ColumnRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private String content;
    private Long pharmacistId;
}
