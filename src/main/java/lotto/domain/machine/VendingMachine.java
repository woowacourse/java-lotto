package lotto.domain.machine;

import lotto.domain.machine.exeption.InvalidManualNumException;
import lotto.domain.ticket.LottoTicketFactory;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.LottoType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private Money money;

    public VendingMachine(Money money) {
        this.money = money;
    }

    public PurchaseInformation createPurchaseInformation(int manualNum, List<List<Integer>> manualNumbers) {
        validateManualNumNotOverWholeNum(manualNum);
        Map<LottoType,Integer> autoManualNumsInformation = new HashMap<>();
        autoManualNumsInformation.put(LottoType.AUTOMATIC, money.getWholeTicketQuantity()-manualNum);
        autoManualNumsInformation.put(LottoType.MANUAL,manualNum);
        return PurchaseInformation.of(autoManualNumsInformation,manualNumbers);
    }

    private void validateManualNumNotOverWholeNum(final int manualNum) {
        if(manualNum> money.getWholeTicketQuantity()){
            throw new InvalidManualNumException("전체 로또 숫자와 같거나 적게 구매 가능합니다");
        }
    }

    public LottoTickets createLotto(PurchaseInformation purchaseInformation) {
        return LottoTicketFactory.of(purchaseInformation);
    }
}
