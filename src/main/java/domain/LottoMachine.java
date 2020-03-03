package domain;

import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private static final int START_INDEX = 0;

    private LottoMachine() {
    }

    public static Lottos createLottos(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = START_INDEX; index < lottoCount.getManualCount(); index++) {
            inputManualNumbersWithValidation(lottos);
        }
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        for (int index = START_INDEX; index < lottoCount.getAutoCount(); index++) {
            lottos.add(lottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }

    private static void inputManualNumbersWithValidation(final List<Lotto> lottos) {
        try {
            LottoGenerator lottoGenerator = new ManualLottoGenerator(InputView.inputManualLottoNumbers());
            lottos.add(lottoGenerator.generateLotto());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputManualNumbersWithValidation(lottos);
        }
    }
}
