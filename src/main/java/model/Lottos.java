package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> manualLottos, int autoPurchaseAmount) {
        List<Lotto> lottos = IntStream.range(0, autoPurchaseAmount).boxed()
                                        .map(i -> Lotto.autoGenerate())
                                        .collect(Collectors.toList());
        lottos.addAll(manualLottos);
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public LottoResult getResult() {
        return new LottoResult(lottos);
    }

    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }
}