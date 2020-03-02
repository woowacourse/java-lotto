package Lotto.domain;

import Lotto.utils.AutoLottosGenerator;
import Lotto.utils.LottosGenerator;
import Lotto.utils.ManualLottosGenerator;

import java.util.List;

public class LottoManager {
    public static Lottos generateManualLottos(List<String> input) {
        LottosGenerator manualLottosGenerator = new ManualLottosGenerator(input);
        return manualLottosGenerator.generate();
    }

    public static Lottos generateAutoLottos(LottoCount lottoCount) {
        LottosGenerator autoLottosGenerator = new AutoLottosGenerator(lottoCount);
        return autoLottosGenerator.generate();
    }

    public static Lottos concatLottos(Lottos autoLottos, Lottos manualLottos) {
        if (manualLottos == null) {
            return autoLottos;
        }
        List<Lotto> allLottos = autoLottos.getLottos();
        allLottos.addAll(manualLottos.getLottos());
        return new Lottos(allLottos);
    }
}
