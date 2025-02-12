package view;

import common.NumberValidator;
import controller.dto.LottoTicketResponse;
import controller.dto.WinningLottoRequest;
import java.util.List;

public class LottoConsoleView {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;

    public LottoConsoleView(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public int requestPurchaseAmount() {
        String rawPurchaseAmount = inputView.inputPurchaseAmount();
        NumberValidator.validateInteger(rawPurchaseAmount);
        int purchaseAmount = Integer.parseInt(rawPurchaseAmount);
        NumberValidator.validatePositive(purchaseAmount);
        return purchaseAmount;
    }

    public void printPurchaseCount(int purchaseCount) {
        outputView.printPurchaseCount(purchaseCount);
    }

    public void printPurchasedLotto(List<LottoTicketResponse> lottoTicketResponses) {
        outputView.printPurchasedLottos(lottoTicketResponses);
    }

    public WinningLottoRequest requestWinningLotto() {
        String rawWinningNumbers = inputView.inputWinningNumbers();
        List<Integer> parsedWinningNumbers = inputParser.parseWinningNumbers(rawWinningNumbers);

        String rawBonusNumber = inputView.inputBonusNumber();
        int parsedBonusNumber = inputParser.parseBonusNumber(rawBonusNumber);

        return new WinningLottoRequest(parsedWinningNumbers, parsedBonusNumber);
    }
}
