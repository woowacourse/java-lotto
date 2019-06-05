package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private static final int ZERO = 0;
    private static final int MONEY_PER_LOTTO = 1_000;

    private Map<Rank, Integer> lottoResult;

    LottoResult() {
        lottoResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            lottoResult.put(rank, ZERO);
        }
    }

    void add(Rank rank) {
        lottoResult.put(rank, lottoResult.get(rank) + 1);
    }

    private String getEachResult(Rank rank) {
        if (rank.equals(Rank.SECOND)) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", rank.getCountOfMatch(), rank.getWinningMoney(), lottoResult.get(rank));
        }
        if (rank.equals(Rank.MISS)) {
            return "";
        }
        return String.format("%d개 일치 (%d원)- %d개\n", rank.getCountOfMatch(), rank.getWinningMoney(), lottoResult.get(rank));
    }

    double getRateOfReturn() {
        long winningMoney = 0;
        for (Rank rank : lottoResult.keySet()) {
            winningMoney += (long) rank.getWinningMoney() * lottoResult.get(rank);
        }
        return (double) winningMoney / (getNumberOfLotto() * MONEY_PER_LOTTO);
    }

    private int getNumberOfLotto() {
        int numberOfLotto = 0;
        for (Integer value : lottoResult.values()) {
            numberOfLotto += value;
        }
        return numberOfLotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Objects.equals(lottoResult, that.lottoResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoResult);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : Rank.values()) {
            stringBuilder.append(getEachResult(rank));
        }
        stringBuilder.append(String.format("총 수익률은 %.1f%%입니다.", getRateOfReturn() * 100));
        return stringBuilder.toString();
    }
}
