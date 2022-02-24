package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.dto.LottoTicketResponse;
import lotto.service.MoneyService;
import lotto.service.PurchaseService;
import lotto.utils.IntegerUtils;

public class AutoPurchaseController implements PurchaseController {

    private final PurchaseService purchaseService;
    private final MoneyService moneyService;

    public AutoPurchaseController(PurchaseService purchaseService, MoneyService moneyService) {
        this.purchaseService = purchaseService;
        this.moneyService = moneyService;
    }

    @Override
    public List<LottoTicketResponse> purchase(String inputMoney) {
        Money money = createMoney(inputMoney);
        moneyService.insert(money);
        List<LottoTicket> tickets = purchaseService.purchaseAndPersist(money);
        return toResponse(tickets);
    }

    private List<LottoTicketResponse> toResponse(List<LottoTicket> tickets) {
        return tickets.stream()
            .map(LottoTicketResponse::from)
            .collect(Collectors.toList());
    }

    private Money createMoney(String inputMoney) {
        return new Money(IntegerUtils.parse(inputMoney));
    }
}
