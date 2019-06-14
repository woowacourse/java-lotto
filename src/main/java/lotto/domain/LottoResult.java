package lotto.domain;

import lotto.dto.LottoResultDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private static final int ZERO = 0;
    private static final int MONEY_PER_LOTTO = 1_000;

    private Map<Rank, Integer> lottoResult;

    public LottoResult() {
        lottoResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            lottoResult.put(rank, ZERO);
        }
    }

    public void add(Rank rank) {
        lottoResult.put(rank, lottoResult.get(rank) + 1);
    }

    public void add(Rank rank, int value) {
        lottoResult.put(rank, value);
    }

    public double getRateOfReturn() {
        return (double) getWinningMoney() / (getNumberOfLotto() * MONEY_PER_LOTTO);
    }

    private long getWinningMoney() {
        long winningMoney = 0;
        for (Rank rank : lottoResult.keySet()) {
            winningMoney += (long) rank.getWinningMoney() * lottoResult.get(rank);
        }
        return winningMoney;
    }

    private int getNumberOfLotto() {
        int numberOfLotto = 0;
        for (Integer value : lottoResult.values()) {
            numberOfLotto += value;
        }
        return numberOfLotto;
    }

    public int getResultByRank(Rank rank) {
        return lottoResult.get(rank);
    }

    public LottoResultDTO toDTO(int round) {
        LottoResultDTO lottoResultDto = new LottoResultDTO();
        lottoResultDto.setRound(round);
        lottoResultDto.setFirst(lottoResult.get(Rank.FIRST));
        lottoResultDto.setSecond(lottoResult.get(Rank.SECOND));
        lottoResultDto.setThird(lottoResult.get(Rank.THIRD));
        lottoResultDto.setFourth(lottoResult.get(Rank.FOURTH));
        lottoResultDto.setFifth(lottoResult.get(Rank.FIFTH));
        lottoResultDto.setMiss(lottoResult.get(Rank.MISS));
        return lottoResultDto;
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
}
