package model;

import java.util.ArrayList;
import java.util.List;

public class LottoWinningNumberDTO {
    private final List<Integer> winningNumbers;

    public LottoWinningNumberDTO(List<Integer> winningNumbers) {
        this.winningNumbers = new ArrayList<>(winningNumbers);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
