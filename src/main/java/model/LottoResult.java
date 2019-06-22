package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoResult implements Iterable<LottoResultPair> {
    private final List<LottoResultPair> table;
    private final Money purchasedAmount;
    private final Money totalAmount;

    public LottoResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        this.table = Collections.unmodifiableList(
                lottos.stream()
                        .map(l -> l.match(winningNumbers))
                        .flatMap(r -> r.map(Stream::of).orElseGet(Stream::empty))
                        .collect(Collectors.groupingBy(LottoRank::prize))
                        .values().stream()
                        .map(l -> new LottoResultPair(l.get(0), l.size()))
                        .collect(Collectors.toList())
        );
        this.purchasedAmount = new Money(Lotto.PRICE * lottos.size());
        this.totalAmount = new Money(
                this.table.stream()
                .map(pair -> pair.rank().prize().amount() * pair.number())
                .reduce(0, (a, b) -> a + b)
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