package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int lottoCounts) {
        generateLottos(lottoCounts);
    }

    private void generateLottos(int lottoCounts) {
        for (int i = 0; i < lottoCounts; i ++) {
            lottos.add(new Lotto());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
            stringBuilder.append(lotto.toString());
        }
        return stringBuilder.toString();
    }
}
