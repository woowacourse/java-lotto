package lotto.domain.generator;


import lotto.domain.Lotto;
import lotto.domain.LottoNo;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {
    private final List<String> lottoNoStrings;
    private final int countOfPurchase;

    private LottosGenerator(final List<String> lottoNoStrings, final int countOfPurchase) {
        this.lottoNoStrings = new ArrayList<>(lottoNoStrings);
        this.countOfPurchase = countOfPurchase;
        validate(lottoNoStrings.size(), countOfPurchase);
    }

    private void validate(final int countOfManual, final int countOfPurchase) {
        if (countOfManual > countOfPurchase) {
            throw new IllegalArgumentException("수동은 총 구매 횟수를 넘으면 안됩니다.");
        }
    }

    public static LottosGenerator of(final List<String> lottoNos, final int countOfPurchase) {
        return new LottosGenerator(lottoNos, countOfPurchase);
    }

    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(addManualLotto());
        lottos.addAll(addAutoLotto(countOfAuto(lottos)));
        return lottos;
    }

    private List<Lotto> addManualLotto() {
        List<Lotto> manualLottos = new ArrayList<>();
        for (final String lottoNo : lottoNoStrings) {
            List<LottoNo> lottoNos = new LottoNosManualGenerator(lottoNo).generate();
            manualLottos.add(Lotto.of(lottoNos));
        }
        return manualLottos;
    }

    private List<Lotto> addAutoLotto(int countOfAuto) {
        List<Lotto> autoLottos = new ArrayList<>();
        LottoNosGenerator lottoNosGenerator = new LottoNosAutoGenerator();
        for (int i = 0; i < countOfAuto; i++) {
            List<LottoNo> lottoNos = lottoNosGenerator.generate();
            autoLottos.add(Lotto.of(lottoNos));
        }
        return autoLottos;
    }

    private int countOfAuto(final List<Lotto> lottos) {
        return countOfPurchase - lottos.size();
    }
}
