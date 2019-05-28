package lotto.domain.generator;


import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {
    private final int countOfManual;
    private final int countOfPurchase;
    private final InputView inputView;

    private LottosGenerator(final int countOfManual, final int countOfPurchase, InputView inputView) {
        validate(countOfManual, countOfPurchase);
        this.countOfManual = countOfManual;
        this.countOfPurchase = countOfPurchase;
        this.inputView = inputView;
    }

    private void validate(final int countOfManual, final int countOfPurchase) {
        if (countOfManual > countOfPurchase) {
            throw new IllegalArgumentException("구입 금액 초과");
        }
    }

    public static LottosGenerator of(final int countOfManual, final int countOfPurchase, InputView inputView) {
        return new LottosGenerator(countOfManual, countOfPurchase, inputView);
    }

    public List<Lotto> generate() {
        List<Lotto> lottos = inputManualLotto();
        addAutoLotto(lottos);
        return lottos;
    }

    private List<Lotto> inputManualLotto() {
        inputView.printInputManual();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < countOfManual; i++) {
            String inputLottoNos = inputView.inputManual();
            List<LottoNo> lottoNos = new LottoNosManualGenerator(inputLottoNos).generate();
            lottos.add(Lotto.of(lottoNos));
        }
        return lottos;
    }

    private void addAutoLotto(final List<Lotto> lottos) {
        LottoNosGenerator lottoNosGenerator = new LottoNosAutoGenerator();
        for (int i = 0; i < countOfPurchase - countOfManual; i++) {
            List<LottoNo> lottoNos = lottoNosGenerator.generate();
            lottos.add(Lotto.of(lottoNos));
        }
    }
}
