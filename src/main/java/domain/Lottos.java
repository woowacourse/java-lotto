package domain;

import static constant.LottoConstants.LOTTO_PRICE;
import static exception.ExceptionMessage.PRICE_RANGE_ERROR;
import static exception.ExceptionMessage.PRICE_UNIT_ERROR;

import exception.LottoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.RandomGenerator;
import util.RandomNumberGenerator;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(int money, RandomGenerator randomGenerator) {
        validateMoney(money);
        int count = money / LOTTO_PRICE.getValue();
        this.lottos = buyLottos(count, randomGenerator);
    }

    private List<Lotto> buyLottos(int count, RandomGenerator randomGenerator) {
        List<Lotto> newLottos = new ArrayList<>();
        while (newLottos.size() != count) {
            newLottos.add(makeUniqueLotto(newLottos, randomGenerator));
        }
        return newLottos;
    }

    private Lotto makeUniqueLotto(final List<Lotto> lottos, RandomGenerator randomGenerator) {
        Lotto newLotto;
        do {
            newLotto = new Lotto(RandomNumberGenerator.getRandomNumbers(randomGenerator));
        } while (isDuplicate(lottos, newLotto));
        return newLotto;
    }

    private void validateMoney(int money) {
        if (money <= 0) {
            throw LottoException.from(PRICE_RANGE_ERROR);
        }
        if (money % LOTTO_PRICE.getValue() != 0) {
            throw LottoException.from(PRICE_UNIT_ERROR);
        }
    }

    private boolean isDuplicate(final List<Lotto> lottos, Lotto newLotto) {
        return lottos.contains(newLotto);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
