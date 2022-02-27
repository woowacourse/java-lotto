package lotto.model;

import static java.util.stream.Collectors.*;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Long> result;

    private LottoResult(Map<Rank, Long> result) {
        this.result = result;
    }

    public static LottoResult create(Lottos lottos, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        EnumMap<Rank, Long> collect = lottos.getLottos().stream()
            .map(lotto -> LottoResult.match(lotto, winningNumbers, bonusNumber))
            .collect(groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), counting()));
        return new LottoResult(collect);
    }

    private static Rank match(Lotto lotto, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        return Rank.find(lotto.matchWinningNumbers(winningNumbers), lotto.matchNumber(bonusNumber));
    }

    public Long getRankCount(Rank rank) {
        return result.getOrDefault(rank, 0L);
    }

    Long getTotalWinningMoney() {
        return result.entrySet().stream()
            .map(entry -> entry.getKey().getMoney() * entry.getValue())
            .mapToLong(i -> i)
            .sum();
    }
}