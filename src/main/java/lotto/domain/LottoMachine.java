package lotto.domain;

public class LottoMachine {
    private final LottoNumberGenerator manualLottoNumberGenerator;
    private final LottoNumberGenerator randomLottoNumberGenerator;

    public LottoMachine(LottoNumberGenerator manualLottoNumberGenerator, LottoNumberGenerator randomLottoNumberGenerator) {
        this.manualLottoNumberGenerator = manualLottoNumberGenerator;
        this.randomLottoNumberGenerator = randomLottoNumberGenerator;
    }

    public LottoTickets buyLottoTickets(PurchasingCounts purchasingCounts) {
        LottoTickets manualLottoTickets = LottoTickets.generateLottoTickets(purchasingCounts.getManualTicketCounts(), manualLottoNumberGenerator);
        LottoTickets autoLottoTickets = LottoTickets.generateLottoTickets(purchasingCounts.getAutoTicketCounts(), randomLottoNumberGenerator);
        return manualLottoTickets.addAll(autoLottoTickets);
    }
}
