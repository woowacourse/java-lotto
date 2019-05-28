package lotto.domain;

import java.util.List;

public class WinnigLotto {
    private final List<Integer> winngLottos;

    public WinnigLotto(List<Integer> lottoNumbers) {
        winngLottos = lottoNumbers;
    }

    public int match(Lotto lotto) {
        int result = 0;
        for (Integer number : lotto.numbers()) {
            if (winngLottos.contains(number)) {
                result++;
            }
        }
        return result;
    }
}
