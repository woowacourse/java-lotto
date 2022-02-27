package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            this.lottos.add(lotto);
        }
    }

    public PrizeResult prizeResult(int inputMoney, WinningNumbers winningNumber) {
        return new PrizeResult(inputMoney, lottos, winningNumber);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

}
