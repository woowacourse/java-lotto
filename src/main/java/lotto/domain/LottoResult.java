package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private final Map<WinningBoard, Integer> results = new LinkedHashMap<>();

    public LottoResult() {
        Arrays.stream(WinningBoard.values())
            .forEach(winningBoard -> this.results.put(winningBoard, 0));
    }

    public void checkWinnings(LottoTickets lottoTickets,
        WinningLotto winningLotto) {
        lottoTickets.getLottoTickets()
            .forEach(ticket -> addWinning(getWinning(winningLotto, ticket)));
    }

    private void addWinning(WinningBoard winningBoard) {
        results.put(winningBoard, results.get(winningBoard) + 1);
    }

    private WinningBoard getWinning(WinningLotto winningLotto, LottoTicket ticket) {
        return WinningBoard.findWinnings(ticket.countHits(winningLotto.getNumbers()),
            winningLotto.hasBonus(ticket));
    }

    public int calculateTotalReward() {
        return results.entrySet()
            .stream()
            .mapToInt(result -> result.getKey().getReward() * result.getValue())
            .sum();
    }

    public Map<WinningBoard, Integer> getResults() {
        return results;
    }
}
