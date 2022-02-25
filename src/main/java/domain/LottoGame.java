package domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LottoGame {

    private final LottoTickets lottoTickets;
    private final WinningLotto referee;
    private final Map<LottoResult, Integer> resultsStatistics = Arrays
            .stream(LottoResult.values())
            .collect(Collectors.toMap(key -> key, value -> 0, (o1, o2) -> o1, TreeMap::new));

    public LottoGame(LottoTickets lottoTickets, WinningLotto referee) {
        this.lottoTickets = lottoTickets;
        this.referee = referee;
        analyzeLottos();
    }

    private void analyzeLottos() {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            LottoResult result = referee.getLottoResult(lottoTicket);
            if (result == null) {
                continue;
            }
            resultsStatistics.put(result, resultsStatistics.get(result) + 1);
        }
    }

    public Map<LottoResult, Integer> getResultStatistics() {
        return resultsStatistics;
    }

    public float calculateProfitRatio() {
        Set<LottoResult> lottoResultKeys = resultsStatistics.keySet();

        int totalPrize = lottoResultKeys.stream()
                .mapToInt(result -> sum(result, resultsStatistics.get(result)))
                .sum();


        return (float) totalPrize / getLottoPrice();
    }

    private int sum(LottoResult lottoResult, int count) {
        return lottoResult.getPrize() * count;
    }

    private int getLottoPrice() {
        return lottoTickets.getLottoTickets().size() * 1000;
    }
}
