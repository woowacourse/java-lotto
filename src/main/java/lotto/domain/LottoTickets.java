package lotto.domain;

import lotto.domain.lottogenerator.LottoGeneratingStrategy;
import lotto.domain.lottogenerator.LottoGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private static final int LOTTO_PRICE = 1_000;

    private List<Lotto> lottoTickets;

    public LottoTickets(Payment payment, LottoGeneratingStrategy strategy) {
        if (Objects.isNull(payment) || Objects.isNull(strategy)) {
            throw new NullPointerException();
        }

        int numberOfTickets = payment.calculateNumberOfTickets(LOTTO_PRICE);

        //TODO mapToObj에서 i 제거
        lottoTickets = IntStream.range(0, numberOfTickets)
                .mapToObj(i -> LottoGenerator.create(strategy))
                .collect(Collectors.toList());
    }

    public int size() {
        return lottoTickets.size();
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}