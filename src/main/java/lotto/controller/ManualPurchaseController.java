package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.config.ServiceConfig;
import lotto.domain.LottoTicket;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.StringInputNumberGenerator;
import lotto.dto.LottoTicketResponse;
import lotto.service.PurchaseService;
import lotto.service.MoneyService;

public class ManualPurchaseController {

    private final MoneyService moneyService;
    private final PurchaseService service;

    public ManualPurchaseController(MoneyService moneyService, PurchaseService service) {
        this.moneyService = moneyService;
        this.service = service;
    }

    private static class ManualPurchaseControllerHelper {
        private static final ManualPurchaseController INSTANCE = new ManualPurchaseController(
            ServiceConfig.getMoneyService(),
            ServiceConfig.getPurchaseService()
        );
    }

    public static ManualPurchaseController getInstance() {
        return ManualPurchaseControllerHelper.INSTANCE;
    }

    public List<LottoTicketResponse> purchase(List<String> inputNumberLines) {
        NumberGenerator generator = new StringInputNumberGenerator(inputNumberLines);
        List<LottoTicket> lottoTickets = service.purchase(generator, inputNumberLines.size());
        return toResponse(lottoTickets);
    }

    private List<LottoTicketResponse> toResponse(List<LottoTicket> tickets) {
        return tickets.stream()
            .map(LottoTicketResponse::from)
            .collect(Collectors.toList());
    }
}
