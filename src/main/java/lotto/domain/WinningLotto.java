package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {
    private final List<Integer> winningNumbers;

    public WinningLotto(List<Integer> winningNumbers) {
        if (winningNumbers.size() != AutoLotto.MAX_LOTTO_SIZE) {
            throw new IllegalArgumentException("당첨 번호의 개수는 6개 입니다.");
        }
        this.winningNumbers = winningNumbers;
    }

    public int matchNumbersOfLotto(List<Integer> userNumbers) {
        Set<Integer> checkLottoNumbers = new HashSet<>(winningNumbers);
        checkLottoNumbers.addAll(userNumbers);
        return winningNumbers.size() + userNumbers.size() - checkLottoNumbers.size();
    }
}
