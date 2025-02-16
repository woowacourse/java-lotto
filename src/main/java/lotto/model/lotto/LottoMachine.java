package lotto.model.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.model.Money;
import lotto.controller.generator.NumbersGenerator;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1_000;

    public Lottos issueAutomatic(final Money buyingAmount, final NumbersGenerator numbersGenerator) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int count = 0; count < buyingAmount.calculateBuyingCount(LOTTO_PRICE); count++) {
            List<Integer> randomLottoNumbers = numbersGenerator.generate();
            lottoTickets.add(new Lotto(randomLottoNumbers));
        }
        return new Lottos(lottoTickets);
    }

}
