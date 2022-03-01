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

    private LottoTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoTickets = generateTickets(lottoCount, lottoNumberGenerator);
    }

    private List<LottoTicket> generateTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        return IntStream.range(LOTTO_COUNT_START_INCLUSIVE, lottoCount)
                .mapToObj(noneUsed -> new LottoTicket(lottoNumberGenerator))
                .collect(toList());
    }

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public static LottoTickets createAutoLottoTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        return new LottoTickets(lottoCount, lottoNumberGenerator);
    }

    public static LottoTickets createManualLottoTickets(List<List<Integer>> manualNumbers) {
        List<LottoTicket> lottoTickets = manualNumbers.stream()
                .map(LottoTicket::new)
                .collect(toList());

        return new LottoTickets(lottoTickets);
    }

    public LottoTickets combine(LottoTickets targetLottoTickets) {
        List<LottoTicket> joined = new ArrayList<>(lottoTickets);
        joined.addAll(targetLottoTickets.lottoTickets);

        return new LottoTickets(joined);
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
