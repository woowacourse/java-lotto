package lotto.domain;

import java.util.*;

public class LottoResult {
    private final Map<WinningPrize, Integer> winningInformation;

    public LottoResult(List<WinningPrize> winningPrizes) {
        this.winningInformation = Collections.unmodifiableMap(makeWinningInformation(winningPrizes));
    }

    private Map<WinningPrize, Integer> makeWinningInformation(List<WinningPrize> winningPrizes) {
        Map<WinningPrize, Integer> winningInformation = new LinkedHashMap<>();
        Arrays.stream(WinningPrize.values()).forEach(winningPrize -> winningInformation.put(winningPrize, 0));
        winningPrizes.forEach(winningPrize -> winningInformation.put(winningPrize, winningInformation.get(winningPrize) + 1));
        return winningInformation;
    }

    public long calculateEarningRate() {
        long totalEarning = 0;
        int totalCount = 0;

        for (WinningPrize winningPrize : WinningPrize.values()) {
            totalCount += winningInformation.get(winningPrize);
            totalEarning += (winningPrize.getPrize() * winningInformation.get(winningPrize));
        }
        return totalEarning / (totalCount * 10);
    }

    public Map<WinningPrize, Integer> getWinningInformation() {
        return this.winningInformation;
    }
}
