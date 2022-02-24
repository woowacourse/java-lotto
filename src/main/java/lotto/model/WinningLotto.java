package lotto.model;

import java.util.List;

import lotto.util.InputValidator;

public class WinningLotto {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<String> winningNumbers, String bonusNumber) {
        InputValidator.validateContain(winningNumbers, bonusNumber);
        this.winningNumbers = InputValidator.validateLotto(winningNumbers);
        this.bonusNumber = InputValidator.validateBonusNumber(bonusNumber);
    }

    public void checkRank(Lottos lottos) {
        lottos.calculateRanks(winningNumbers, bonusNumber);
    }
}
