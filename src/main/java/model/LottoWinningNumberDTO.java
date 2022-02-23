package model;

import java.util.List;

public class LottoWinningNumberDTO {
    private final List<Integer> winningNumbers;

    public LottoWinningNumberDTO(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
