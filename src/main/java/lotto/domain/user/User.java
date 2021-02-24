package lotto.domain.user;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottogenerator.RandomLottoGenerator;
import lotto.exception.LessThanLottoPriceException;
import lotto.model.Money;

public class User {

    private Money balance;
    private Lottos manualLottos = Lottos.EMPTY_LOTTOS;
    private Lottos automaticLottos = Lottos.EMPTY_LOTTOS;

    public User(Money balance) {
        this.balance = balance;
    }

    public void buyManualLottos(List<Lotto> lottoNumbers) {
        int priceOfLottos = lottoNumbers.size() * Lotto.LOTTO_PRICE;
        if (balance.isLessThan(priceOfLottos)) {
            throw new LessThanLottoPriceException();
        }
        balance = balance.minus(priceOfLottos);
        manualLottos = new Lottos(lottoNumbers);
    }

    public void buyAutomaticLottos() {
        int numOfAvailableLotto = balance.getPrice() / Lotto.LOTTO_PRICE;
        List<Lotto> availableLotto = Stream.iterate(0, i -> i+1)
            .map(i -> Lotto.generatedBy(new RandomLottoGenerator()))
            .limit(numOfAvailableLotto)
            .collect(Collectors.toList());
        automaticLottos = new Lottos(availableLotto);
    }

    public int getNumOfManualLottos() {
        return this.manualLottos.getNumOfLottos();
    }

    public int getNumOfAutomaticLottos() {
        return this.automaticLottos.getNumOfLottos();
    }

    public Lottos getManualLottos() {
        return new Lottos(manualLottos.getLottos());
    }

    public Lottos getAutomaticLottos() {
        return new Lottos(automaticLottos.getLottos());
    }
}
