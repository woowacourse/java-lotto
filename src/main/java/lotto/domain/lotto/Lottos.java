package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final String ERROR_WRONG_INPUT_MONEY = "[ERROR] 올바른 구매 값을 입력해주세요";

    private List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public Lottos(final int count) {
        checkCount(count);

        for (int i = 0; i < count; i++) {
            Lotto lotto = new Lotto();
            lotto.generateRandomNumbers();
            lottos.add(lotto);
        }
    }

    private static void checkCount(final int count) {
        if (count < 1) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_MONEY);
        }
    }

    public void addLotto(final Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Lottos addLottos(List<Lotto> addLottos) {
        this.lottos.addAll(addLottos);
        return this;
    }
}
