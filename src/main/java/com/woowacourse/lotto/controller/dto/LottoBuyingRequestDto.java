package com.woowacourse.lotto.controller.dto;

import java.util.List;

public class LottoBuyingRequestDto {
    private int buyingMoney;
    private List<List<Integer>> manualNumbers;

    public int getBuyingMoney() {
        return buyingMoney;
    }

    public void setBuyingMoney(int buyingMoney) {
        this.buyingMoney = buyingMoney;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }

    public void setManualNumbers(List<List<Integer>> manualNumbers) {
        this.manualNumbers = manualNumbers;
    }
}
