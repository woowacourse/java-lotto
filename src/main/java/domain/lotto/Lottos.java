package domain.lotto;

import static domain.lotto.Lotto.LOTTO_PRICE;
import static domain.lotto.Lotto.MAX_LOTTO_NUMBER;
import static domain.lotto.Lotto.MAX_LOTTO_SIZE;
import static domain.lotto.Lotto.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public static Lottos issue(int money, NumberGenerator numberGenerator) {
        int lottoQuantity = money / LOTTO_PRICE;

        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < lottoQuantity; i++) {
            List<Integer> lottoNumbers = numberGenerator.generate(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, MAX_LOTTO_SIZE);
            generatedLottos.add(new Lotto(lottoNumbers));
        }
        return new Lottos(generatedLottos);
    }

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
