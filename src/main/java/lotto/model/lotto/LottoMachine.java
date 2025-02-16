package lotto.model.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.controller.generator.NumbersGenerator;
import lotto.model.Money;
import lotto.view.util.NumberFormatter;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1_000;

    private final Money buyingAmount;

    public LottoMachine(final Money buyingAmount) {
        validateMinimumBuyingAmount(buyingAmount);
        this.buyingAmount = buyingAmount;
    }

    private void validateMinimumBuyingAmount(final Money buyingAmount) {
        if (buyingAmount.isLessThan(LOTTO_PRICE)) {
            throw new IllegalArgumentException(
                    "최소 구매 금액은 %s원 입니다.".formatted(NumberFormatter.formatMoney(LOTTO_PRICE))
            );
        }
    }

    public List<Lotto> issueAutomatic(final NumbersGenerator numbersGenerator) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int count = 0; count < buyingAmount.calculateBuyingCount(LOTTO_PRICE); count++) {
            List<Integer> randomLottoNumbers = numbersGenerator.generate();
            lottoTickets.add(new Lotto(randomLottoNumbers));
        }
        return lottoTickets;
    }

    public int calculateChange() {
        return buyingAmount.calculateChange(LOTTO_PRICE);
    }

}
