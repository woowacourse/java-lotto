package lotto.domain.lotto;

import lotto.domain.Money;


import java.util.ArrayList;
import java.util.List;

import static lotto.domain.lotto.LottoTicketGenerator.PRICE_EACH_LOTTO;


public class LottoTicketBuyingRequest {
    private static final String ERROR_MANUAL_LOTTO_PRICE_OVER = "[ERROR] 총 구입 금액보다 수동구입량이 많습니다.";

    private Money money;
    private LottoAmount manualLottoAmount;
    private List<LottoLine> manualLottoLineList;

    public LottoTicketBuyingRequest(Money money, LottoAmount manualLottoAmount) {
        if (manualLottoAmount.getValue() * PRICE_EACH_LOTTO > money.getValue()) {
            throw new IllegalArgumentException(ERROR_MANUAL_LOTTO_PRICE_OVER);
        }

        this.money = money;
        this.manualLottoAmount = manualLottoAmount;
        this.manualLottoLineList = new ArrayList();
    }

    public void submitManualLottoLine(LottoLine manualLottoLine) {
        manualLottoLineList.add(manualLottoLine);
    }

    public List<LottoLine> getManualLottoLineList() {
        return manualLottoLineList;
    }

    public int getManualLottoAmount() {
        return manualLottoAmount.getValue();
    }

    public int getAutoLottoAmount() {
        int moneyValue = money.getValue();
        int manualLottoAmountValue = manualLottoAmount.getValue();
        return new LottoAmount( moneyValue / PRICE_EACH_LOTTO - manualLottoAmountValue).getValue();
    }
}
