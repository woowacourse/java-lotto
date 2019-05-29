package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private final List<Integer> winningLotto;
    private final int bonus;

    public WinningLotto(List<Integer> lottoNumbers, int bonus) {
        winningLotto = lottoNumbers;
        this.bonus = bonus;
    }

    public WinningLotto(List<String> inputs) {
        winningLotto = splitNumbers(inputs.get(0));
        try {
            bonus = Integer.parseInt(inputs.get(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        if (winningLotto.size() != 6) {
            throw new IllegalArgumentException();
        }

        if (winningLotto.contains(bonus)) {
            throw new IllegalArgumentException();
        }
    }

    private List<Integer> splitNumbers(String numbers) {
        try {
            return Arrays.stream(numbers.split(","))
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
