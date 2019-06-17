package lotto.model;

import lotto.model.lottomachine.AutomaticLottoMachine;
import lotto.model.lottomachine.LottoMachine;
import lotto.model.lottomachine.ManualLottoMachine;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    public static Lottos produceLottos(Money money, List<String> manualLottos) {
        List<Lotto> finalLottos = new ArrayList<>();
        addManualLottos(finalLottos, manualLottos);
        addAutoLottos(finalLottos, money.calculateAutomaticLottoCount(manualLottos.size()));
        return new Lottos(finalLottos);
    }

    private static void addManualLottos(List<Lotto> finalLottos, List<String> manualLottos) {
        for (String currentManualLotto : manualLottos) {
            LottoMachine manualLottoMachine = new ManualLottoMachine(currentManualLotto);
            finalLottos.add(manualLottoMachine.generateLotto());
        }
    }

    private static void addAutoLottos(List<Lotto> finalLottos, int autoCount) {
        for (int i = 0; i < autoCount; i++) {
            LottoMachine automaticLottoMachine = new AutomaticLottoMachine();
            finalLottos.add(automaticLottoMachine.generateLotto());
        }
    }

}
