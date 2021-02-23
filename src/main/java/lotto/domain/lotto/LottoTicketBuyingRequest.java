package lotto.domain.lotto;

import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.lotto.LottoTicketGenerator.PRICE_EACH_LOTTO;

public class LottoTicketBuyingRequest {
    private static final String ERROR_MANUAL_LOTTO_PRICE_OVER = "총 구입 금액보다 수동구입량이 많습니다.";
    private Money money;
    private int numberOfManualLotto;
    private List<LottoLine> manualLottoLineList;

    public LottoTicketBuyingRequest(Money money, int numberOfManualLotto) {
        if (numberOfManualLotto * PRICE_EACH_LOTTO > money.getValue()) {
            throw new IllegalArgumentException();
        }

        this.money = money;
        this.numberOfManualLotto = numberOfManualLotto;
        this.manualLottoLineList = new ArrayList<>();
    }

    public void submitManualLottoLine(LottoLine manualLottoLine) {
        manualLottoLineList.add(manualLottoLine);
    }

    public Money getRemainMoney() {
        return new Money(money.getValue() - numberOfManualLotto * PRICE_EACH_LOTTO);
    }
}
