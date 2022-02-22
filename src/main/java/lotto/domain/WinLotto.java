package lotto.domain;

import java.util.List;

public class WinLotto {

    private final List<Integer> winNumbers;

    public WinLotto(final List<Integer> winNumbers) {
        checkDuplicateWinNumbers(winNumbers);
        this.winNumbers = winNumbers;
    }

    private void checkDuplicateWinNumbers(final List<Integer> winNumbers) {
        if (winNumbers.size() != calculateDistinctSize(winNumbers)) {
            throw new IllegalArgumentException("[ERROR] 중복된 당첨 번호가 존재합니다.");
        }
    }

    private long calculateDistinctSize(List<Integer> winNumbers) {
        return winNumbers.stream()
                .distinct()
                .count();
    }
}
