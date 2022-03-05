package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WinningResult {
    private static final double ROUND_UNIT = 100.0;

    private final Map<Rank, WinningCount> winningResult;
    private final LottoQuantity purchasedLottoQuantity;

    public WinningResult(Lottos lottos, WinningLotto winningLotto) {
        winningResult = generateWinningResult(lottos, winningLotto);
        purchasedLottoQuantity = generateLottoQuantityByLottos(lottos);
    }

    private Map<Rank, WinningCount> generateWinningResult(Lottos lottos, WinningLotto winningLotto) {
        Map<Rank, Long> winningResultWithLongValue = groupRankByCount(lottos, winningLotto);
        Map<Rank, WinningCount> winningResultWithoutDefault = mapValueToWinningCount(winningResultWithLongValue);

        return putDefaultWinningCount(winningResultWithoutDefault);
    }

    private Map<Rank, Long> groupRankByCount(Lottos lottos, WinningLotto winningLotto) {
        return lottos.getLottos()
                .stream()
                .collect(Collectors.groupingBy(
                        lotto -> Rank.createByLottoAndWinningLotto(lotto, winningLotto),
                        Collectors.counting()
                ));
    }

    private Map<Rank, WinningCount> mapValueToWinningCount(Map<Rank, Long> rankCount) {
        return rankCount.entrySet()
                .stream().collect(Collectors.toMap(
                        Entry::getKey,
                        entrySet -> new WinningCount(entrySet.getValue().intValue())
                ));
    }

    private Map<Rank, WinningCount> putDefaultWinningCount(Map<Rank, WinningCount> winningResultWithoutDefault) {
        Map<Rank, WinningCount> winningResult = new HashMap<>(winningResultWithoutDefault);
        for (Rank rank : Rank.values()) {
            winningResult.putIfAbsent(rank, new WinningCount(0));
        }

        return winningResult;
    }

    private LottoQuantity generateLottoQuantityByLottos(Lottos lottos) {
        return LottoQuantity.from(lottos.getLottos().size());
    }

    public double getProfitRatio() {
        long totalPrice = calculateTotalPrize();
        double purchaseMoney = purchasedLottoQuantity.getLottoQuantity() * Lotto.SINGLE_LOTTO_PRICE;

        return roundToSecondDigit(totalPrice / purchaseMoney);
    }

    private long calculateTotalPrize() {
        return winningResult.entrySet()
                .stream()
                .map((entrySet) -> entrySet.getKey().getPrize() * entrySet.getValue().getCount())
                .reduce(0L, Long::sum);
    }

    private double roundToSecondDigit(double number) {
        return Math.round(number * ROUND_UNIT) / ROUND_UNIT;
    }

    public Map<Rank, WinningCount> getWinningResult() {
        return winningResult;
    }

    @Override
    public String toString() {
        return "WinningResult{" +
                "winningResult=" + winningResult +
                '}';
    }
}
