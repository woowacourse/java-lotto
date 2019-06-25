package lotto.dto;

import java.util.List;

public class HistoryDTO {
    private List<List<Integer>> lottoNumbers;
    private List<Integer> winningNumbers;
    private List<Integer> prize;
    private Double winningRate;

    public List<List<Integer>> getLottoNumbers() {
        return lottoNumbers;
    }

    public void setLottoNumbers(final List<List<Integer>> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(final List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public List<Integer> getPrize() {
        return prize;
    }

    public void setPrize(final List<Integer> prize) {
        this.prize = prize;
    }

    public Double getWinningRate() {
        return winningRate;
    }

    public void setWinningRate(final Double winningRate) {
        this.winningRate = winningRate;
    }
}
