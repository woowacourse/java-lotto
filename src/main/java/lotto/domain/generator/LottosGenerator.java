package lotto.domain.generator;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {
    public static List<Lotto> generate(List<String> manualInputLottos, int totalLottoCount) {
        List<Lotto> totalLottos = new ArrayList<>();
        totalLottos.addAll(generateManualLottos(manualInputLottos));
        totalLottos.addAll(generateRandomLottos(totalLottoCount - manualInputLottos.size()));
        return totalLottos;
    }

    private static List<Lotto> generateManualLottos(List<String> manualInputLottos) {
        List<Lotto> totalLottos = new ArrayList<>();
        for (String manualInputLotto : manualInputLottos) {
            totalLottos.add(generateManualLotto(manualInputLotto));
        }
        return totalLottos;
    }

    private static Lotto generateManualLotto(String manualInputLotto) {
        return new Lotto(LottoManualGenerator.generate(manualInputLotto));
    }

    private static List<Lotto> generateRandomLottos(int randomLottoCount) {
        List<Lotto> totalLottos = new ArrayList<>();
        for (int i = 0; i < randomLottoCount; i++) {
            totalLottos.add(generateRandomLotto());
        }
        return totalLottos;
    }

    private static Lotto generateRandomLotto() {
        return new Lotto(LottoRandomGenerator.generate());
    }
}
