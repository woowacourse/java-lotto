package lotto.domain.machine;

import lotto.domain.machine.exeption.InvalidManualNumException;
import lotto.domain.ticket.LottoTicketFactory;
import lotto.domain.ticket.LottoTickets;

import java.util.List;
import java.util.stream.Collectors;

public class VendingMachine {
    private final Money money;

    public VendingMachine(Money money) {
        this.money = money;
    }

    public Purchase createPurchase(int manualTicketQuantity, List<List<Integer>> multipleManualIntegers) {
        validateManualNumNotOverWholeNum(manualTicketQuantity);
        List<ManualNumbers> multipleManualNumbers = multipleManualIntegers.stream()
                .map(ManualNumbers::of)
                .collect(Collectors.toList());
        return Purchase.of(money.ticketQuantity(), manualTicketQuantity, multipleManualNumbers);
    }

    private void validateManualNumNotOverWholeNum(final int manualTicketQuantity) {
        if (manualTicketQuantity > money.ticketQuantity()) {
            throw new InvalidManualNumException("전체 로또 숫자와 같거나 적게 구매 가능합니다");
        }
    }

    public LottoTickets createLotto(Purchase purchase) {
        return LottoTicketFactory.of(purchase);
    }
}
