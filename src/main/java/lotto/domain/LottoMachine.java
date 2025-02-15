package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoMachine {
    private LottoMachine() {}

    public static List<Lotto> issueLottos(final int lottoAmount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoAmount; i++) {
            Lotto lotto = new Lotto(NumbersGenerator.generateLottoNumbers());
            lottos.add(lotto);
        }
        return lottos;
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
        // TODO : 아래 로직에 대한 테스트
        // method 자체가 private static이어서 테스트할 수 없고,
        // 두 List의 교집합을 올바르게 반환하는지 테스트하고 싶음
        List<Integer> matchNumbers = new ArrayList<>(winningNumbers);
        matchNumbers.retainAll(lottoNumbers);
        int matchCount = matchNumbers.size();
        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        return Prize.getPrize(matchCount, hasBonusNumber);
    }
}
