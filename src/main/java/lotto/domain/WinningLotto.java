package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private final List<Integer> winningLotto;

    public WinningLotto(List<Integer> lottoNumbers) {
        winningLotto = lottoNumbers;
    }

    public WinningLotto(String inputNumbers) {
        try {
            winningLotto = Arrays.stream(inputNumbers.split(","))
                    .map(number -> Integer.parseInt(number))
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
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
