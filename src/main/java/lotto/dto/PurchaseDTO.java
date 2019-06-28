package lotto.dto;

import lotto.domain.ticket.LottoTickets;

public class PurchaseDTO {
    private int round;
    private int manualQuantity;
    private int autoQuantity;
    private LottoTickets lottoTickets;

    public PurchaseDTO(final int round, final int manualQuantity, final int autoQuantity, final LottoTickets lottoTickets) {
        this.round = round;
        this.manualQuantity = manualQuantity;
        this.autoQuantity = autoQuantity;
        this.lottoTickets = lottoTickets;
    }

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public int getManualQuantity() {
        return manualQuantity;
    }

    public void setManualQuantity(final int manualQuantity) {
        this.manualQuantity = manualQuantity;
    }

    public int getAutoQuantity() {
        return autoQuantity;
    }

    public void setAutoQuantity(final int autoQuantity) {
        this.autoQuantity = autoQuantity;
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }

    public void setLottoTickets(final LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets;
    }
}
