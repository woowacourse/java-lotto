package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoMachine implements LottoMachine {

    private final int numberOfAutoLotto;

    public AutoLottoMachine(int numberOfAutoLotto) {
        this.numberOfAutoLotto = numberOfAutoLotto;
    }

    @Override
    public List<Lotto> generateLottos() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfAutoLotto; i++) {
            lottos.add(new Lotto(RandomNumberGenerator.generate()));
        }
        return lottos;
    }
}
