package lotto.domain;

import lotto.domain.lottogenerator.LottoGeneratingStrategy;
import lotto.domain.lottogenerator.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private static final int LOTTO_PRICE = 1_000;

    private List<Lotto> lottoTickets;

    public LottoTickets(Payment payment, LottoGeneratingStrategy strategy) {
        lottoTickets = new ArrayList<>();
        int numberOfTickets = payment.calculateNumberOfTickets(LOTTO_PRICE);

        for (int i = 0; i < numberOfTickets; i++) {
            lottoTickets.add(LottoGenerator.create(strategy));
        }
    }

    public int size() {
        return lottoTickets.size();
    }

}