package lotto.model;

import java.util.List;
import java.util.stream.IntStream;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.generator.NumberGenerator;
import lotto.model.money.Money;
import lotto.model.winning_lotto.WinningLotto;

public class LottoMachine {

    private static final Money LOTTO_PRICE = Money.from(1_000);
    private final NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> issueLottos(final int purchaseMoney) {

        int amount = Money.from(purchaseMoney).divide(LOTTO_PRICE);

        return IntStream.range(0, amount)
                .mapToObj(i -> Lotto.generateFrom(numberGenerator))
                .toList();
    }

    public List<LottoPrize> calculateLottoResults(List<Lotto> lottos, WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> LottoPrize.determine(lotto, winningLotto))
                .toList();
    }
}
