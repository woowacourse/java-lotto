package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos implements Iterable<Lotto> {

    private final List<Lotto> lottos;
    private final LottoCount lottoCount;

    private Lottos(List<Lotto> lottos, LottoCount lottoCount) {
        this.lottos = Collections.unmodifiableList(new ArrayList<>(lottos));
        this.lottoCount = lottoCount;
    }

    public static Lottos createLottos(List<String> manualLotto, LottoCount count) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(createManualLottos(manualLotto, count));
        lottos.addAll(createRandomLottos(count));
        return new Lottos(lottos, count);
    }

    private static List<Lotto> createManualLottos(List<String> manualLotto, LottoCount count) {
        count.validManualLottoCount(manualLotto);
        List<Lotto> lottos = manualLotto.stream()
            .map(rawManualLotto -> Lotto.of(rawManualLotto))
            .collect(Collectors.toList());
        return lottos;
    }

    private static List<Lotto> createRandomLottos(LottoCount count) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = count.getAutoLottoCount();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(Lotto.newAutoLotto());
        }
        return lottos;
    }

    public LottoCount getCount() {
        return lottoCount;
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }
}
