package view;

import common.NumberValidator;
import controller.dto.LottoRankResponse;
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

    public int readPaidAmount() {
        String rawPaidAmount = inputView.readPaidAmount();
        NumberValidator.validateInteger(rawPaidAmount);
        int paidAmount = Integer.parseInt(rawPaidAmount);
        NumberValidator.validatePositive(paidAmount);
        return paidAmount;
    }

    public void printPurchasedTicketAmount(int purchasedTicketAmount) {
        outputView.printPurchasedTicketAmount(purchasedTicketAmount);
    }

    public void printPurchasedLotto(List<LottoTicketResponse> lottoTicketResponses) {
        outputView.printPurchasedLottos(lottoTicketResponses);
    }

    public WinningLottoRequest readWinningLotto() {
        String rawWinningNumbers = inputView.readWinningNumbers();
        List<Integer> parsedWinningNumbers = inputParser.parseWinningNumbers(rawWinningNumbers);

        String rawBonusNumber = inputView.readBonusNumber();
        int parsedBonusNumber = inputParser.parseBonusNumber(rawBonusNumber);

        return new WinningLottoRequest(parsedWinningNumbers, parsedBonusNumber);
    }

    public void printLottoRankResults(List<LottoRankResponse> lottoRankResponses) {
        outputView.printLottoRankResults(lottoRankResponses);
    }

    public void printProfitRate(double profitRate) {
        outputView.printProfitRate(profitRate);
    }
}
