package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//Todo: 추상화 단계 하나 높여서 위에서 Map 주입해주는 방식으로 변경
public class LottoResult {
    private final Map<Prize, Long> lottoResult;

    public LottoResult(Map<Prize, Long> lottoResult) {
        this.lottoResult = new HashMap<>(lottoResult);
    }

    private Map<Prize, Long> groupingRank(List<LottoTicket> lottoTickets,
        WinningLottoTicket winningLottoTicket) {
        return lottoTickets.stream()
            .map(winningLottoTicket::compareNumbers)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Long calculatePrizeMoney() {
        return lottoResult.keySet().stream()
            .mapToLong(key -> key.getPrizeMoney() * lottoResult.get(key))
            .sum();
    }

    public Map<Prize, Long> getLottoResult() {
        return new HashMap<>(lottoResult);
    }
}
