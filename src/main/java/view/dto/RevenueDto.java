package view.dto;

import java.math.BigDecimal;

public class RevenueDto {
    private final BigDecimal revenue;

    public RevenueDto(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getRevenueDto() {
        return this.revenue;
    }
}
