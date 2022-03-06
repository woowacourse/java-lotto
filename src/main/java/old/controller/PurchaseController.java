package old.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import old.domain.LottoMachine;
import old.domain.LottoNumber;
import old.domain.LottoTicket;
import old.domain.Money;
import old.domain.generator.RandomNumberGenerator;
import old.domain.generator.StringInputNumberGenerator;
import old.dto.LottoTicketResponse;
import old.dto.PurchaseResult;
import old.utils.IntegerUtils;
import old.view.ErrorView;
import old.view.InputView;
import old.view.OutputView;

public class PurchaseController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorView errorView;

    private PurchaseController(InputView inputView, OutputView outputView, ErrorView errorView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorView = errorView;
    }

    private static class LottoControllerHelper {
        private static final PurchaseController INSTANCE = new PurchaseController(
            InputView.getInstance(), OutputView.getInstance(), ErrorView.getInstance()
        );
    }

    public static PurchaseController getInstance() {
        return LottoControllerHelper.INSTANCE;
    }

    public PurchaseResult purchase() {
        try {
            Money money = insertMoney();
            LottoMachine lottoMachine = new LottoMachine(money);
            List<LottoTicket> tickets = purchaseLottos(lottoMachine);
            return new PurchaseResult(money, tickets);
        } catch (IllegalArgumentException e) {
            errorView.error(e.getMessage());
            return purchase();
        }
    }

    private Money insertMoney() {
        int parse = IntegerUtils.parse(inputView.inputMoney());
        return Money.from(parse);
    }

    private List<LottoTicket> purchaseLottos(LottoMachine lottoMachine) {
        List<String> inputNumbers = inputView.inputLottoNumbers();

        List<LottoTicket> manualTickets = purchaseManual(lottoMachine, inputNumbers);
        List<LottoTicket> autoTickets = purchaseAuto(lottoMachine);

        List<LottoTicketResponse> manualResponses = toTicketResponse(manualTickets);
        List<LottoTicketResponse> autoResponses = toTicketResponse(autoTickets);

        outputView.outputTickets(manualResponses, autoResponses);
        return Stream.concat(manualTickets.stream(), autoTickets.stream()).collect(Collectors.toList());
    }

    private List<LottoTicket> purchaseManual(LottoMachine lottoMachine, List<String> inputNumbers) {
        return lottoMachine.createLottos(new StringInputNumberGenerator(inputNumbers), inputNumbers.size());
    }

    private List<LottoTicket> purchaseAuto(LottoMachine lottoMachine) {
        return lottoMachine.createLottos(
            new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX), LottoMachine.ALL);
    }

    private List<LottoTicketResponse> toTicketResponse(List<LottoTicket> tickets) {
        return tickets.stream()
            .map(LottoTicketResponse::from)
            .collect(Collectors.toList());
    }

}
