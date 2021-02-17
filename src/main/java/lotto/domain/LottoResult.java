package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

//Todo: 추상화 단계 하나 높여서 위에서 Map 주입해주는 방식으로 변경
public class LottoResult {
    private final Map<Prize, Long> lottoResult;
    private final Long prizeMoneySum;

    public LottoResult(List<LottoTicket> lottoTickets, WinningLottoTicket winningLottoTicket) {
        lottoResult = groupingRank(lottoTickets, winningLottoTicket);
        prizeMoneySum = calculatePrizeMoney(lottoTickets, winningLottoTicket);
    }

    public Map<Prize, Long> groupingRank(List<LottoTicket> lottoTickets, WinningLottoTicket winningLottoTicket) {
        return lottoTickets.stream()
            .map(winningLottoTicket::compareNumbers)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Long calculatePrizeMoney(List<LottoTicket> lottoTickets, WinningLottoTicket winningLottoTicket) {
        return lottoTickets.stream()
            .map(winningLottoTicket::compareNumbers)
            .mapToLong(Prize::getPrizeMoney)
            .sum();
    }

    public Map<Prize, Long> getLottoResult() {
        return new HashMap<>(lottoResult);
    }
}
