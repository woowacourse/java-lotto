package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.config.ServiceConfig;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.dto.LottoTicketResponse;
import lotto.service.PurchaseService;
import lotto.service.MoneyService;
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
        Money money = insertMoney(inputMoney);
        int ticketCount = calculatePurchasableCount(money);
        List<LottoTicket> tickets = purchaseTicketByMoney(ticketCount);
        return toResponse(tickets);
    }

    private Money insertMoney(String inputMoney) {
        Money money = createMoney(inputMoney);
        moneyService.insert(money);
        return money;
    }

    private Money createMoney(String inputMoney) {
        return new Money(IntegerUtils.parse(inputMoney));
    }

    private int calculatePurchasableCount(Money money) {
        return money.divide(Money.from(LottoTicket.PRICE)).getAmount();
    }

    private List<LottoTicket> purchaseTicketByMoney(int ticketCount) {
        NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);
        return purchaseService.purchase(generator, ticketCount);
    }

    private List<LottoTicketResponse> toResponse(List<LottoTicket> tickets) {
        return tickets.stream()
            .map(LottoTicketResponse::from)
            .collect(Collectors.toList());
    }

}
