package lotto.model.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.controller.generator.NumbersGenerator;
import lotto.model.Money;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1_000;

    public List<Lotto> issueAutomatic(final Money buyingAmount, final NumbersGenerator numbersGenerator) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int count = 0; count < buyingAmount.calculateBuyingCount(LOTTO_PRICE); count++) {
            List<Integer> randomLottoNumbers = numbersGenerator.generate();
            lottoTickets.add(new Lotto(randomLottoNumbers));
        }
        return lottoTickets;
    }

    public int calculateChange(final Money buyingAmount) {
        return buyingAmount.calculateChange(LOTTO_PRICE);
    }

}
