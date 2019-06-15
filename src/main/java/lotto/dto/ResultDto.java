package lotto.dto;

import java.util.List;

public class ResultDto {
    private List<String> winnerResults;
    private String returnRate;

    public ResultDto(List<String> winnerResults, String returnRate) {
        this.winnerResults = winnerResults;
        this.returnRate = returnRate;
    }

    public String getReturnRate() {
        return returnRate;
    }

    public List<String> getWinnerResults() {
        return winnerResults;
    }
}
