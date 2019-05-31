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

    public WinningLotto(WinningLottoDto dto) {
        winningLotto = splitNumbers(dto.numbers());
        try {
            bonus = Integer.parseInt(dto.bonus());
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
        boolean bonus = false;
        if (lotto.numbers().contains(bonus)) {
            bonus = true;
        }
        for (Integer number : lotto.numbers()) {
            if (winningLotto.contains(number)) {
                result++;
            }
        }
        return Reward.valueOf(result, bonus).money();
    }
}
