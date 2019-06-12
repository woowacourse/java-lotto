package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoResult implements Iterable<LottoResultPair> {
    private final List<LottoResultPair> table;
    private final Money purchasedAmount;
    private final Money totalAmount;

    public LottoResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<LottoRank, Integer> pairs = new LinkedHashMap<LottoRank, Integer>() {{
            Stream.of(LottoRank.values()).forEach(rank -> put(rank, 0));
            lottos.forEach(lotto -> lotto.match(winningNumbers).map(x -> put(x, get(x) + 1)));
        }};
        this.table = generateTable(pairs);
        this.purchasedAmount = new Money(Lotto.PRICE * lottos.size());
        this.totalAmount = new Money(pairs.entrySet().stream()
                                    .mapToInt(x -> x.getKey().prize().amount() * x.getValue())
                                    .sum());
    }

    private List<LottoResultPair> generateTable(Map<LottoRank, Integer> pairs) {
        return Collections.unmodifiableList(
                pairs.entrySet().stream()
                .map(x -> new LottoResultPair(x.getKey(), x.getValue()))
                .collect(Collectors.toList())
        );
    }

    public Money purchasedAmount() {
        return this.purchasedAmount;
    }

    public Money totalAmount() {
        return this.totalAmount;
    }

    public double earningRate() {
        return this.totalAmount.earningRate(this.purchasedAmount);
    }

    @Override
    public Iterator<LottoResultPair> iterator() {
        return table.iterator();
    }
}