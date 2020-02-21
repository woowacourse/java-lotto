package lotto.domain;

import java.util.List;
import java.util.Set;

class LottoComparator {
    static int compare(Lotto lotto, Set<Integer> winningNumbers) {
        return lotto.matchWinningNumbers(winningNumbers);
    }
}
