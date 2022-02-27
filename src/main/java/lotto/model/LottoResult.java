package lotto.model;

import static java.util.stream.Collectors.*;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private final Map<Rank, Long> result;

    private LottoResult(Map<Rank, Long> result) {
        this.result = result;
    }

    public static LottoResult create(Lottos lottos, Lotto winningNumbers, LottoNumber bonusNumber) {
        EnumMap<Rank, Long> collect = lottos.getLottos().stream()
            .map(lotto -> Rank.match(lotto, winningNumbers, bonusNumber))
            .collect(groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), counting()));
        return new LottoResult(collect);
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