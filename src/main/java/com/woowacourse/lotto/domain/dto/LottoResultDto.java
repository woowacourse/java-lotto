package com.woowacourse.lotto.domain.dto;

public class LottoResultDto {
    private String resultMsg;
    private String profitRatio;

    public LottoResultDto(String resultMsg, String profitRatio) {
        this.resultMsg = resultMsg;
        this.profitRatio = profitRatio;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(String profitRatio) {
        this.profitRatio = profitRatio;
    }
}
