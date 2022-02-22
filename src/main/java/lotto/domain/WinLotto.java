package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinLotto {

    private final List<Integer> winNumbers;

    public WinLotto(final List<Integer> winNumbers) {
        checkDuplicateWinNumbers(winNumbers);
        this.winNumbers = winNumbers;
    }

    private void checkDuplicateWinNumbers(final List<Integer> winNumbers) {
        final Set<Integer> winNumberSet = new HashSet<>(winNumbers);

        if (winNumbers.size() != winNumberSet.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 당첨 번호가 존재합니다.");
        }
    }
}
