package domain;

import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private static final int START_INDEX = 0;
    private List<Lotto> lottos = new ArrayList<>();

    public LottoMachine(final LottoCount lottoCount) {
        for (int index = START_INDEX; index < lottoCount.getManualCount(); index++) {
            inputManualNumbersWithValidation(lottos);
        }
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        for (int index = START_INDEX; index < lottoCount.getAutoCount(); index++) {
            lottos.add(lottoGenerator.generateLotto());
        }
    }

    private void inputManualNumbersWithValidation(final List<Lotto> lottos) {
        try {
            LottoGenerator lottoGenerator = new ManualLottoGenerator(InputView.inputManualLottoNumbers());
            lottos.add(lottoGenerator.generateLotto());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputManualNumbersWithValidation(lottos);
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
