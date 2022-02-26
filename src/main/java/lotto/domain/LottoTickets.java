package lotto.domain;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoTickets {

    private static final int LOTTO_COUNT_START_INCLUSIVE = 0;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoTickets = generateTickets(lottoCount, lottoNumberGenerator);
    }

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    private List<LottoTicket> generateTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        return IntStream.range(LOTTO_COUNT_START_INCLUSIVE, lottoCount)
                .mapToObj(value -> new LottoTicket(lottoNumberGenerator))
                .collect(toList());
    }

    public LottoResult determine(WinningNumber winningNumber) {
        Map<Rank, Long> ranks = lottoTickets.stream()
                .map(winningNumber::compare)
                .collect(groupingBy(identity(), counting()));

        return new LottoResult(ranks);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
