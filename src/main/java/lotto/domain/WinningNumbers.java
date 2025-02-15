package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.Lotto.validateLottoNumber;

public class WinningNumbers {
    private final List<Integer> winningNumbers;

    public WinningNumbers(final List<Integer> winningNumbers) {
        validateWinningNumbers(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validateWinningNumbers(final List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("%d개의 고유한 번호를 입력해야 합니다.".formatted(LOTTO_SIZE));
        }

        for (final int winningNumber : winningNumbers) {
            validateLottoNumber(winningNumber);
        }
    }

    public void validateBonusNumberDuplicated(final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    public int calculateMatchCount(final List<Integer> lottos) {
        List<Integer> matchNumbers = new ArrayList<>(winningNumbers);
        matchNumbers.retainAll(lottos);
        return matchNumbers.size();
    }
}
