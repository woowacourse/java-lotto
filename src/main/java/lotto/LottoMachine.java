package lotto;

import static lotto.Lotto.LOTTO_SIZE;
import static lotto.Lotto.MAX_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LottoMachine {
    public static Lotto createLotto() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_SIZE) {
            int number = new Random().nextInt(MAX_LOTTO_NUMBER - 1) + 1;
            numbers.add(number);
        }
        return new Lotto(numbers);
    }

    public static WinningStatistics calculateStatistics(final List<Lotto> lottos, final WinningNumbers winningNumbers,
                                                        final int bonusNumber) {
        Map<Prize, Integer> statistics = new HashMap<>();
        for (final Prize prize : Prize.values()) {
            statistics.put(prize, 0);
        }

        for (final Lotto lotto : lottos) {
            Prize prize = calculate(lotto.getNumbers(), winningNumbers.getWinningNumbers(), bonusNumber);
            statistics.put(prize, statistics.get(prize) + 1);
        }
        return new WinningStatistics(statistics);
    }

    private static Prize calculate(final List<Integer> lottoNumbers, final List<Integer> winningNumbers,
                                   final int bonusNumber) {
        List<Integer> matchNumbers = new ArrayList<>(winningNumbers);
        matchNumbers.retainAll(lottoNumbers);
        int matchCount = matchNumbers.size();
        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        return Prize.getPrize(matchCount, hasBonusNumber);
    }
}
