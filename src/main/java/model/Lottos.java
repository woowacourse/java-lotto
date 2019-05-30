package model;

import java.util.*;
import java.util.stream.IntStream;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> manualLottos, int autoPurchaseAmount) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottos);
        IntStream.range(0, autoPurchaseAmount).boxed()
                .forEach(i -> lottos.add(Lotto.autoGenerate()));
        this.lottos = Collections.unmodifiableList(lottos);
    }

    /*
    public LottoResult getResult(Set<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        return new LottoResult(lottos, winningNumbers, bonusNumber);
    }
    */

    public LottoResult getResult() {
        return new LottoResult(lottos);
    }

    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }
}