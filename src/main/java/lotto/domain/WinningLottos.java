package lotto.domain;

import java.util.List;

public class WinningLottos {
    private final List<Integer> winningLottos;

    public WinningLottos(List<Integer> lottoNumbers) {
        winningLottos = lottoNumbers;
    }

    public int match(Lotto lotto) {
        int result = 0;
        for (Integer number : lotto.numbers()) {
            if (winningLottos.contains(number)) {
                result++;
            }
        }
        return result;
    }
}
