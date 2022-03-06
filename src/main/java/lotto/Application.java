package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.WinningTicket;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.domain.generator.StringInputNumberGenerator;
import lotto.dto.LottoStatisticsResponse;
import lotto.dto.LottoTicketResponse;
import lotto.dto.PurchaseResult;
import lotto.utils.IntegerUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    private final InputView inputView;
    private final OutputView outputView;

    private Application(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private static class ApplicationHelper {
        private static final Application INSTANCE = new Application(
            InputView.getInstance(), OutputView.getInstance()
        );
    }

    public static void main(String[] args) {
        Application application = ApplicationHelper.INSTANCE;
        application.run();
    }

    private void run() {
        PurchaseResult purchaseResult = tryPurchase();
        tryDrawLots(purchaseResult);
    }

    private PurchaseResult tryPurchase() {
        Money money = Money.from(inputView.inputMoney());
        LottoMachine lottoMachine = new LottoMachine(money);
        List<LottoTicket> tickets = purchaseLottos(lottoMachine);
        return new PurchaseResult(money, tickets);
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

    public void tryDrawLots(PurchaseResult purchaseResult) {
        try {
            WinningTicket winningTicket = createWinningTicket();
            showStatistics(winningTicket, purchaseResult);
        } catch (IllegalArgumentException e) {
            outputView.outputError(e);
            tryDrawLots(purchaseResult);
        }
    }

    private WinningTicket createWinningTicket() {
        LottoTicket winningNumber = createWinningNumber();
        LottoNumber bonusBall = createWinningBonus();
        return new WinningTicket(winningNumber, bonusBall);
    }

    private LottoTicket createWinningNumber() {
        String inputWinningNumber = inputView.inputWinningNumber();
        List<LottoNumber> numbers = IntegerUtils.parseAll(inputWinningNumber)
            .stream()
            .map(LottoNumber::from)
            .collect(Collectors.toList());
        return new LottoTicket(numbers);
    }

    private LottoNumber createWinningBonus() {
        String inputBonusBall = inputView.inputBonusBall();
        return LottoNumber.from(IntegerUtils.parse(inputBonusBall));
    }

    private void showStatistics(WinningTicket winningTicket, PurchaseResult purchaseResult) {
        LottoStatisticsResponse statistics = toStatisticsResponse(winningTicket, purchaseResult);
        outputView.outputStatistics(statistics);
    }

    private LottoStatisticsResponse toStatisticsResponse(
        WinningTicket winningTicket, PurchaseResult purchaseResult) {
        List<LottoRank> ranks = purchaseResult.getTickets().stream()
            .map(winningTicket::compare)
            .collect(Collectors.toList());
        return new LottoStatisticsResponse(ranks, purchaseResult.getMoney());
    }

}
