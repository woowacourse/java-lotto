package service.dto;

import java.math.BigDecimal;

public class LottoRankDto {
    private final String name;
    private final BigDecimal prize;

    public LottoRankDto(String name, BigDecimal prize) {
        this.name = name;
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrize() {
        return prize;
    }
}
