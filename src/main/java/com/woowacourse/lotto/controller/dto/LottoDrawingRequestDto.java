package com.woowacourse.lotto.controller.dto;

import java.util.List;

public class LottoDrawingRequestDto {
    private List<Long> lottos;
    private List<Integer> winningNumbers;
    private int winningBonusNumber;

    public List<Long> getLottos() {
        return lottos;
    }

    public void setLottos(List<Long> lottos) {
        this.lottos = lottos;
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
}
