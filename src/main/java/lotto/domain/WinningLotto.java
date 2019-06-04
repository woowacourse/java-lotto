package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String DELIMETER = ",";
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
        if (winningLotto.size() != Lotto.NUMBER_COUNT) {
            throw new IllegalArgumentException();
        }

        if (winningLotto.contains(bonus)) {
            throw new IllegalArgumentException();
        }
    }

    private List<Integer> splitNumbers(String numbers) {
        try {
            return Arrays.stream(numbers.split(DELIMETER))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public Reward match(Lotto lotto) {
        boolean hasBonusNumber = false;
        if (lotto.numbers().contains(this.bonus)) {
            hasBonusNumber = true;
        }
        return Reward.valueOf(matchCount(lotto), hasBonusNumber);
    }

    private int matchCount(Lotto lotto) {
        Set<Integer> lottoNumberSet = new HashSet<>(winningLotto);
        lottoNumberSet.addAll(lotto.numbers());
        return 12 - lottoNumberSet.size();
    }
}
