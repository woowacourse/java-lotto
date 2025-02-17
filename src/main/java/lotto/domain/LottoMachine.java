package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LottoMachine {
    private LottoMachine() {}

    public static List<Lotto> issueLottos(final int lottoAmount) {
        return Stream.generate(NumbersGenerator::generateLottoNumbers)
                .map(Lotto::new)
                .limit(lottoAmount)
                .toList();
    }

    public static WinningStatistics calculateStatistics(final List<Lotto> lottos, final WinningNumbers winningNumbers,
                                                        final BonusNumber bonusNumber) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (final Lotto lotto : lottos) {
            int matchCount = winningNumbers.calculateMatchCount(lotto.getNumbers());
            boolean hasBonusNumber = bonusNumber.isIncludedIn(lotto);
            Rank rank = Rank.getRank(matchCount, hasBonusNumber);
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }
        return new WinningStatistics(statistics);
    }
}
