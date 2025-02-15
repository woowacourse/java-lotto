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
        Map<Rank, Integer> statistics = new HashMap<>();
        for (final Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        for (final Lotto lotto : lottos) {
            Rank rank = calculate(lotto.getNumbers(), winningNumbers.getWinningNumbers(), bonusNumber);
            statistics.put(rank, statistics.get(rank) + 1);
        }
        return new WinningStatistics(statistics);
    }

    private static Rank calculate(final List<Integer> lottoNumbers, final List<Integer> winningNumbers,
                                  final int bonusNumber) {
        // TODO : 아래 로직에 대한 테스트
        // method 자체가 private static이어서 테스트할 수 없고,
        // 두 List의 교집합을 올바르게 반환하는지 테스트하고 싶음
        List<Integer> matchNumbers = new ArrayList<>(winningNumbers);
        matchNumbers.retainAll(lottoNumbers);
        int matchCount = matchNumbers.size();
        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        return Rank.checkRank(matchCount, hasBonusNumber);
    }
}
