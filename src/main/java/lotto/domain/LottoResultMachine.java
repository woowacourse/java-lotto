package lotto.domain;

import java.util.Map;
import java.util.stream.Collectors;

public class LottoResultMachine {
    public static Map<Rank, Integer> confirmResult(LottoTickets lottoTickets, WinningTicket winningTicket) {
        return lottoTickets.toList().stream()
                .collect(Collectors.groupingBy(winningTicket::getLottoResult, Collectors.summingInt(x -> 1)));
    }
}
