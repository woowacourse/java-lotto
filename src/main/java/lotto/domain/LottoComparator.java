package lotto.domain;

import java.util.List;

public class LottoComparator {
    static int compare(Lotto lotto, List<Integer> winningNumbers, int bonusBall) {
        return lotto.matchWinningNumbers(winningNumbers);
    }
}
