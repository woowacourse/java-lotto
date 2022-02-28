package lotto.controller.dto;

public class PurchaseInfoDto {

    private final int money;
    private final int manualCount;
    private final LottoTicketsDto manualNumbers;

    private PurchaseInfoDto(int money, int manualCount, LottoTicketsDto manualNumbers) {
        this.money = money;
        this.manualCount = manualCount;
        this.manualNumbers = manualNumbers;
    }

    public static PurchaseInfoDto valueOf(int money, int manualCount, LottoTicketsDto manualNumbers) {
        return new PurchaseInfoDto(money, manualCount, manualNumbers);
    }

    public int getMoney() {
        return money;
    }

    public int getManualCount() {
        return manualCount;
    }

    public LottoTicketsDto getManualNumbers() {
        return manualNumbers;
    }
}
