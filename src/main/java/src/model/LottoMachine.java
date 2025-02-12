package src.model;

import java.util.List;
import java.util.stream.IntStream;
import src.model.generator.NumberGenerator;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1_000;
    private final NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> issueLottos(final int purchaseMoney) {
        validatePurchaseMoney(purchaseMoney);
        int amount = purchaseMoney / LOTTO_PRICE;

        return IntStream.range(0, amount)
                .mapToObj(i -> Lotto.generateFrom(numberGenerator))
                .toList();
    }

    private void validatePurchaseMoney(final int purchaseMoney) {
        if (purchaseMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또 구매 금액이 나누어떨어지지 않습니다.");
        }

        if (purchaseMoney < 0) {
            throw new IllegalArgumentException("로또 구매 금액은 음수가 될 수 없습니다.");
        }
    }
}
