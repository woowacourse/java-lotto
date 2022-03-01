package lotto.controller.dto;

public class SalesInfoDto {

    private final int manualCount;
    private final int autoCount;
    private final LottoTicketsDto lottoTickets;

    public SalesInfoDto(int manualCount, int autoCount, LottoTicketsDto lottoTickets) {
        this.manualCount = manualCount;
        this.autoCount = autoCount;
        this.lottoTickets = lottoTickets;
    }

    public static SalesInfoDto valueOf(int manualCount, int autoCount, LottoTicketsDto lottoTickets) {
        return new SalesInfoDto(manualCount, autoCount, lottoTickets);
    }
}
