package domain;

import view.InputView;

import java.util.ArrayList;
import java.util.List;

public class ManualLottosGenerator implements LottosGenerator {

    private static LottoGenerator lottoGenerator;

    @Override
    public List<Lotto> generateLottos(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = START_INDEX; index < lottoCount.getManualCount(); index++) {
            lottoGenerator = new ManualLottoGenerator(InputView.inputManualLottoNumbers(1));
            lottos.add(lottoGenerator.generateLotto());
        }
        return lottos;
    }
}
