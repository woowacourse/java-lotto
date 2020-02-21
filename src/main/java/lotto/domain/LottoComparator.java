package lotto.domain;

import java.util.List;

class LottoComparator {
    static int compare(Lotto lotto, List<Integer> winningNumbers) {
        return lotto.matchWinningNumbers(winningNumbers);
    }
}
