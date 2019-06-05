package lotto.domain.utils;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {

    private int manualLottoSize;

    public ManualLottoGenerator(int manualLottoSize) {
        this.manualLottoSize = manualLottoSize;
    }

    @Override
    public List<Lotto> generate(List<Number> lottoNumber) {
        List<Lotto> manualLottos = new ArrayList<>();
        Lotto lotto;

        for (int i = 1; i <= manualLottoSize; i++) {
            lotto = new Lotto(lottoNumber);
            manualLottos.add(lotto);
        }
        return manualLottos;
    }

}
