package service.dto;

import java.math.BigDecimal;
import java.util.Map;

public class LottoResultDto {
    private Map<String, Integer> result;
    private BigDecimal profitRate;

    public LottoResultDto(Map<String, Integer> result, BigDecimal profitRate) {
        this.result = result;
        this.profitRate = profitRate;
    }

    public Map<String, Integer> getResult() {
        return result;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }
}
