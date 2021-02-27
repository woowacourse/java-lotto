package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottogenerator.RandomLottoGenerator;
import lotto.exception.LessThanLottoPriceException;

public class LottoMachine {

    private Money balance;

    private LottoMachine(Money insertMoney) {
        this.balance = insertMoney;
    }

    public static LottoMachine insertMoney(Money insertMoney) {
        return new LottoMachine(insertMoney);
    }

    public Lottos buyManualLottos(List<Lotto> lottoNumbers) {
        int priceOfLottos = lottoNumbers.size() * Lotto.LOTTO_PRICE;
        if (balance.isLessThan(priceOfLottos)) {
            throw new LessThanLottoPriceException();
        }
        balance = balance.minus(priceOfLottos);
        return Lottos.from(lottoNumbers);
    }

    public Lottos buyAutomaticLottos() {
        int numOfAvailableLotto = balance.getPrice() / Lotto.LOTTO_PRICE;
        List<Lotto> availableLotto = Stream.iterate(0, i -> i + 1)
            .map(i -> Lotto.generatedBy(new RandomLottoGenerator()))
            .limit(numOfAvailableLotto)
            .collect(Collectors.toList());
        return Lottos.from(availableLotto);
    }
}
