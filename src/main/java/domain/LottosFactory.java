package domain;

import view.InputView;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    public static List<Lotto> createAutoLottos(final int autoLottoCount) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int index = 0; index < autoLottoCount; index++) {
            autoLottos.add(LottoFactory.createOneLotto());
        }
        return autoLottos;
    }

    public static List<Lotto> createManualLottos() {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int index = 0; index < ManualCount.getManualCount(); index++) {
            manualLottos.add(LottoFactory
                    .createOneManualLotto(InputView.inputManualLottoNumbers()));
        }
        return manualLottos;
    }

}
