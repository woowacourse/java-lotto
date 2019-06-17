package lotto.dto;

import lotto.domain.lotto.LottoTicketGroup;

public class PurchaseInformationDTO {
    private int manualQuantity;
    private int autoQuantity;
    private LottoTicketGroup lottos;
    private int change;

    public PurchaseInformationDTO(int manualQuantity, int autoQuantity, LottoTicketGroup lottos, int change) {
        this.manualQuantity = manualQuantity;
        this.autoQuantity = autoQuantity;
        this.lottos = lottos;
        this.change = change;
    }

    public int getManualQuantity() {
        return manualQuantity;
    }

    public void setManualQuantity(int manualQuantity) {
        this.manualQuantity = manualQuantity;
    }

    public int getAutoQuantity() {
        return autoQuantity;
    }

    public void setAutoQuantity(int autoQuantity) {
        this.autoQuantity = autoQuantity;
    }

    public LottoTicketGroup getLottos() {
        return lottos;
    }

    public void setLottos(LottoTicketGroup lottos) {
        this.lottos = lottos;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

}
