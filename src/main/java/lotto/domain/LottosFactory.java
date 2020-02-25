package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    public static final String INVALID_MANUAL_LOTTOS_COUNT = "수동 로또 개수(%d)와 정보 개수(%d)가 일치하지 않습니다.";

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
            lottos.add(LottoFactory.createManual(manualLotto.get(index)));
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

    public static Lottos createAutoLottos(LottoCount count) {
        return new Lottos(createRandomLottos(count), count);
    }

    private static List<Lotto> createRandomLottos(LottoCount count) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = count.getAutoLottoCount();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(LottoFactory.createRandom());
        }
        return lottos;
    }
}
