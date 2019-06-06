package com.woowacourse.lotto.controller.dto;

import java.util.List;

public class ManualLottoBuyingRequestDto {
    private List<List<Integer>> numbers;

    public List<List<Integer>> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<List<Integer>> numbers) {
        this.numbers = numbers;
    }
}
