package lotto.domain.generate;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Price;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {
    private static final int MIN_SELF_LOTTO_COUNT = 0;

    private int countOfSelf;

    public LottosFactory(Price price, int countOfSelf) {
        checkCountOfSelf(price, countOfSelf);
        this.countOfSelf = countOfSelf;
    }

    private void checkCountOfSelf(Price price, int inputSelfCount) {
        if (inputSelfCount < MIN_SELF_LOTTO_COUNT || inputSelfCount > price.getCountOfLotto()) {
            throw new IllegalArgumentException("올바르지 않은 횟수입니다.");
        }
    }

    public Lottos generateLottos(List<String> selfInputs, Price price) {
        List<Lotto> lottos = new ArrayList<>();
        addSelfLotto(selfInputs, lottos);
        addAutoLotto(price, lottos);
        return new Lottos(lottos);
    }

    private void addAutoLotto(Price price, List<Lotto> lottos) {
        AutoLottoFactory autoLottoFactory = new AutoLottoFactory();
        for (int i = countOfSelf; i < price.getCountOfLotto(); i++) {
            lottos.add(autoLottoFactory.create());
        }
    }

    private void addSelfLotto(List<String> selfInputs, List<Lotto> lottos) {
        for (int i = 0; i < countOfSelf; i++) {
            lottos.add(new SelfLottoFactory(selfInputs.get(i)).create());
        }
    }

    public int getCountOfSelf() {
        return countOfSelf;
    }
}
