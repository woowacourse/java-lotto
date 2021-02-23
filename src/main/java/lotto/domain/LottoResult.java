package lotto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
        return WinningBoard.findWinnings(countHits(winningLotto, ticket),
            winningLotto.hasBonus(ticket));
    }

    private int countHits(WinningLotto winningLotto, LottoTicket ticket) {
        Set<Integer> hitLottoNumbers = new HashSet<>(ticket.getLottoNumbers());
        hitLottoNumbers.retainAll(winningLotto.getNumbers().getLottoNumbers());
        return hitLottoNumbers.size();
    }

    public int calculateTotalReward() {
        return results.entrySet()
            .stream()
            .mapToInt(this::calculateReward)
            .sum();
    }

    private int calculateReward(Map.Entry<WinningBoard, Integer> result) {
        return result.getKey().getReward() * result.getValue();
    }

    public Map<WinningBoard, Integer> getResults() {
        return results;
    }
}
