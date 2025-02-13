package lotto;

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
        while (numbers.size() < 6) {
            int number = new Random().nextInt(44) + 1;
            numbers.add(number);
        }
        return new Lotto(numbers);
    }

    public static Map<Prize, Integer> calculateStatistics(final List<Lotto> lottos, final WinningNumbers winningNumbers,
                                                          final int bonusNumber) {
        Map<Prize, Integer> statistics = new HashMap<>();
        for (Prize prize : Prize.values()) {
            statistics.put(prize, 0);
        }

        for (Lotto lotto : lottos) {
            Prize prize = calculate(lotto.getNumbers(), winningNumbers.getWinningNumbers(), bonusNumber);
            statistics.put(prize, statistics.get(prize) + 1);
        }
        return statistics;
    }

    private static Prize calculate(List<Integer> lottoNumbers, List<Integer> winningNumbers, int bonusNumber) {
        List<Integer> matchNumbers = new ArrayList<>(winningNumbers);
        matchNumbers.retainAll(lottoNumbers);
        int matchCount = matchNumbers.size();
        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        return Prize.getPrize(matchCount, hasBonusNumber);
    }
}
