package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private static LottoMachine lottoMachine;

    private LottoMachine() {
    }

    public static LottoMachine getInstance() {
        if (lottoMachine == null) {
            lottoMachine = new LottoMachine();
        }
        return lottoMachine;
    }

    public List<Lotto> createLottos(LottoGenerator lottoGenerator, int numberOfLottoToCreate) {
        ArrayList<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLottoToCreate; i++) {
            lottos.add(lottoGenerator.createLotto());
        }
        return lottos;
    }
}
