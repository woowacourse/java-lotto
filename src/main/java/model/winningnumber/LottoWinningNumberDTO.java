package model.winningnumber;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class LottoWinningNumberDTO {
    private final Set<Integer> winningNumbers;

    public LottoWinningNumberDTO(Set<Integer> winningNumbers) {
        this.winningNumbers = new HashSet<>(winningNumbers);
    }

    public Set<Integer> getWinningNumbers() {
        return Collections.unmodifiableSet(winningNumbers);
    }
}
