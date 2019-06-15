package lotto.dto;

import lotto.domain.Prize;
import lotto.domain.Result;

import java.util.HashMap;
import java.util.Map;

public class ResultDto {
    private Map<String, Integer> prizeResult;
    private int winningMoney;

    public ResultDto() {
    }

    public ResultDto(final Map<String, Integer> prizeResult, final int winningMoney) {
        this.prizeResult = prizeResult;
        this.winningMoney = winningMoney;
    }

    public Map<String, Integer> getPrizeResult() {
        return prizeResult;
    }

    public void setPrizeResult(final Map<String, Integer> prizeResult) {
        this.prizeResult = prizeResult;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public void setWinningMoney(final int winningMoney) {
        this.winningMoney = winningMoney;
    }

    public Result toEntity() {
        Map<Prize, Integer> wrappedPrizeResult = new HashMap<>();

        for (Prize prize : Prize.values()) {
            wrappedPrizeResult.put(prize, prizeResult.get(prize.name().toLowerCase()));
        }

        return new Result(wrappedPrizeResult);
    }
}
