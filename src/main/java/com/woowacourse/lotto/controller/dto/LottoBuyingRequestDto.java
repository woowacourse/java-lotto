package com.woowacourse.lotto.controller.dto;

import java.util.List;

public class LottoBuyingRequestDto {
    private int buyingMoney;
    private List<Integer> winningNumbers;
    private int winningBonusNumber;
    private List<List<Integer>> manualNumbers;

    public int getBuyingMoney() {
        return buyingMoney;
    }

    public void setBuyingMoney(int buyingMoney) {
        this.buyingMoney = buyingMoney;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int getWinningBonusNumber() {
        return winningBonusNumber;
    }

    public void setWinningBonusNumber(int winningBonusNumber) {
        this.winningBonusNumber = winningBonusNumber;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }

    public void setManualNumbers(List<List<Integer>> manualNumbers) {
        this.manualNumbers = manualNumbers;
    }
}
