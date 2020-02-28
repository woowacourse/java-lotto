package domain;

import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ManualLottosGenerator implements LottosGenerator {

    @Override
    public List<Lotto> generateLottos(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = START_INDEX; index < lottoCount.getManualCount(); index++) {
            inputManualNumbersWithValidation(lottos);
        }
        return lottos;
    }

    private void inputManualNumbersWithValidation(List<Lotto> lottos) {
        try {
            LottoGenerator lottoGenerator = new ManualLottoGenerator(InputView.inputManualLottoNumbers());
            lottos.add(lottoGenerator.generateLotto());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputManualNumbersWithValidation(lottos);
        }
    }
}
