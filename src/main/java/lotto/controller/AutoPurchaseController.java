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

    public List<LottoTicketResponse> purchase(int purchasedCount) {
        int ticketCount = calculatePurchasableCount(purchasedCount);
        List<LottoTicket> tickets = purchaseTicketByMoney(ticketCount);
        return toResponse(tickets);
    }

    private int calculatePurchasableCount(int purchasedCount) {
        int initialCount = moneyService.inquire().getAmount() / LottoTicket.PRICE;
        return initialCount - purchasedCount;
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
