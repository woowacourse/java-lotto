package lotto;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoCreator implements LottoCreator {
    private LottoNumbers numbers;

    public AutoLottoCreator() {
        this.numbers = new LottoNumbers();
    }

    @Override
    public List<Lotto> createLottos(int lottoQuantity) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoQuantity; i++) {
            lottos.add(new Lotto(numbers.getLottoNumbers()));
        }
        return lottos;
    }
}
