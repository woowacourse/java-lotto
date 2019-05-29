package lotto.domain;

import lotto.domain.lottogenerator.LottoGeneratingStrategy;
import lotto.domain.lottogenerator.LottoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoTickets {
    private static final int LOTTO_PRICE = 1_000;

    private List<Lotto> lottoTickets;

    public LottoTickets(Payment payment, LottoGeneratingStrategy strategy) {
        if (Objects.isNull(payment) || Objects.isNull(strategy)) {
            throw new NullPointerException();
        }

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