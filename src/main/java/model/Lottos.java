package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> manualLottos, LottoPurchaseAmount purchaseAmount) {
        List<Lotto> lottos = IntStream.range(0, purchaseAmount.auto()).boxed()
                                        .map(i -> Lotto.autoGenerate())
                                        .collect(Collectors.toList());
        lottos.addAll(manualLottos);
        if (lottos.isEmpty() || (manualLottos.size() != purchaseAmount.manual())) {
            throw new IllegalArgumentException();
        }
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public Lottos(LottoPurchaseAmount purchaseAmount) {
        this(new ArrayList<>(), purchaseAmount);
    }

    public Lottos(String encoded) {
        this.lottos = Collections.unmodifiableList(
                Stream.of(encoded.split("]"))
                .map(l -> new Lotto(l))
                .collect(Collectors.toList())
        );
    }

    public LottoResult getResult(WinningNumbers winningNumbers) {
        return new LottoResult(this.lottos, winningNumbers);
    }

    public String encodeToDB() {
        return this.lottos.stream().map(Lotto::encodeToDB).reduce("", String::concat);
    }

    public int amount() {
        return lottos.size();
    }

    public Iterator<Lotto> iterator() {
        return this.lottos.iterator();
    }

    @Override
    public String toString() {
        return lottos.toString();
    }
}