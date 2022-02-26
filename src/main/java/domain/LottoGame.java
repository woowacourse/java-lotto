package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LottoGame {

    public static final int ADDITION = 1;

    private final LottoTickets lottoTickets;
    private final WinningLotto winningLotto;
    private final Map<LottoResult, Integer> resultsStatistics = Arrays
            .stream(LottoResult.values())
            .collect(Collectors.toMap(key -> key, value -> 0, (o1, o2) -> o1, TreeMap::new));

    public LottoGame(LottoTickets lottoTickets, WinningLotto winningLotto) {
        this.lottoTickets = lottoTickets;
        this.winningLotto = winningLotto;
        analyzeLottoTickets();
    }

    private void analyzeLottoTickets() {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            LottoResult result = winningLotto.getLottoResult(lottoTicket);
            if (result == null) {
                continue;
            }
            resultsStatistics.put(result, resultsStatistics.get(result) + ADDITION);
        }
    }

    public float calculateProfitRatio(int initialPrice) {
        Set<LottoResult> lottoResultKeys = resultsStatistics.keySet();

        int totalPrize = lottoResultKeys.stream()
                .mapToInt(result -> multiply(result.getPrize(), resultsStatistics.get(result)))
                .sum();

        return (float) totalPrize / initialPrice;
    }

    private int multiply(int prize, int count) {
        return prize * count;
    }

    public Map<LottoResult, Integer> getResultStatistics() {
        return Collections.unmodifiableMap(resultsStatistics);
    }
}
