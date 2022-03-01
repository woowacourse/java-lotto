package lotto.controller.dto;

public class SalesInfoDto {

    private final int money;
    private final int manualCount;
    private final int autoCount;
    private final LottoTicketsDto lottoTickets;

    private SalesInfoDto(int money, int manualCount, int autoCount, LottoTicketsDto lottoTickets) {
        this.money = money;
        this.manualCount = manualCount;
        this.autoCount = autoCount;
        this.lottoTickets = lottoTickets;
    }

    public static SalesInfoDto valueOf(int money, int manualCount, int autoCount, LottoTicketsDto lottoTickets) {
        return new SalesInfoDto(money, manualCount, autoCount, lottoTickets);
    }

    public int getMoney() {
        return money;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public LottoTicketsDto getLottoTickets() {
        return lottoTickets;
    }
}
