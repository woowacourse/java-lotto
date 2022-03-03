package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.config.ServiceConfig;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.dto.LottoTicketResponse;
import lotto.service.MoneyService;
import lotto.service.PurchaseService;
import lotto.utils.IntegerUtils;

public class AutoPurchaseController {

    private final PurchaseService purchaseService;
    private final MoneyService moneyService;

    private AutoPurchaseController(PurchaseService purchaseService, MoneyService moneyService) {
        this.purchaseService = purchaseService;
        this.moneyService = moneyService;
    }

    private static class AutoPurchaseControllerHelper {
        private static final AutoPurchaseController INSTANCE = new AutoPurchaseController(
            ServiceConfig.getPurchaseService(),
            ServiceConfig.getMoneyService()
        );
    }

    public static AutoPurchaseController getInstance() {
        return AutoPurchaseControllerHelper.INSTANCE;
    }

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
