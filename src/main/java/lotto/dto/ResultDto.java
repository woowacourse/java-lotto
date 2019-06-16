package lotto.dto;

import java.util.List;

public class ResultDto {
    private final int lottoRound;
    private final List<String> winnerResults;
    private final String returnRate;

    public ResultDto(List<String> winnerResults, String returnRate, int lottoRound) {
        this.winnerResults = winnerResults;
        this.returnRate = returnRate;
        this.lottoRound = lottoRound;
    }

    public String getReturnRate() {
        return returnRate;
    }

    public List<String> getWinnerResults() {
        return winnerResults;
    }

    public int getLottoRound() {
        return lottoRound;
    }
}
