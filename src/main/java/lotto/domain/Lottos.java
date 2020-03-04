package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {

    private static final String INVALID_MANUAL_LOTTOS_COUNT
        = "수동 로또 개수(%d)와 정보 개수(%d)가 일치하지 않습니다.";

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
        validManualLottoSize(manualLotto, count);
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = manualLotto.size();
        for (int index = 0; index < lottoCount; index++) {
            lottos.add(Lotto.of(manualLotto.get(index)));
        }
        return lottos;
    }

    private static void validManualLottoSize(List<String> manualLotto, LottoCount count) {
        if (manualLotto.size() != count.getManualLottoCount()) {
            throw new IllegalArgumentException(String
                .format(INVALID_MANUAL_LOTTOS_COUNT, manualLotto.size(),
                    count.getManualLottoCount()));
        }
    }

    private static List<Lotto> createRandomLottos(LottoCount count) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = count.getAutoLottoCount();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(Lotto.newAutoLotto());
        }
        return lottos;
    }

    public boolean isSameCount(int count) {
        return lottos.size() == count;
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }

    public LottoCount getCount() {
        return lottoCount;
    }
}
