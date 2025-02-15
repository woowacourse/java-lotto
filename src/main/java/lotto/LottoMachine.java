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

    public static WinningStatistics calculateStatistics(final List<Lotto> lottos, final WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = new HashMap<>();
        for (final Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        for (final Lotto lotto : lottos) {
            Rank rank = checkRank(lotto.getNumbers(), winningLotto.getWinningNumbers(), winningLotto.getBonusNumber());
            statistics.put(rank, statistics.get(rank) + 1);
        }
        return new WinningStatistics(statistics);
    }

    private static Rank checkRank(final List<Integer> lottoNumbers, final List<Integer> winningNumbers,
                                  final int bonusNumber) {
        List<Integer> matchNumbers = new ArrayList<>(winningNumbers);
        matchNumbers.retainAll(lottoNumbers);
        int matchCount = matchNumbers.size();
        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        return Rank.checkRank(matchCount, hasBonusNumber);
    }
}
