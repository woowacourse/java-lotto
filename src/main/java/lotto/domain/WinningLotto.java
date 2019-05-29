package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final List<Integer> winningLotto;

    public WinningLotto(List<Integer> lottoNumbers) {
        winningLotto = lottoNumbers;
    }

    public int match(Lotto lotto) {
        int result = 0;
        for (Integer number : lotto.numbers()) {
            if (winningLotto.contains(number)) {
                result++;
            }
        }
        return Reward.valueOf(result).money();
    }
}
