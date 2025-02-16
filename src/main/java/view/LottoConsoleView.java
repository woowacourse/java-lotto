package view;

import controller.dto.LottoRankResponse;
import controller.dto.LottoTicketResponse;
import controller.dto.WinningLottoRequest;
import java.util.List;

public class LottoConsoleView {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoParser lottoParser;

    public LottoConsoleView(InputView inputView, OutputView outputView, LottoParser lottoParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoParser = lottoParser;
    }

    public int requestPurchaseAmount() {
        String rawPurchaseAmount = inputView.inputPurchaseAmount();
        return lottoParser.parsePurchaseAmount(rawPurchaseAmount);
    }

    public void printPurchaseCount(int purchaseCount) {
        outputView.printPurchaseCount(purchaseCount);
    }

    public void printPurchasedLotto(List<LottoTicketResponse> lottoTicketResponses) {
        outputView.printPurchasedLottos(lottoTicketResponses);
    }

    public WinningLottoRequest requestWinningLotto() {
        String rawWinningNumbers = inputView.inputWinningNumbers();
        List<Integer> parsedWinningNumbers = lottoParser.parseWinningNumbers(rawWinningNumbers);

        String rawBonusNumber = inputView.inputBonusNumber();
        int parsedBonusNumber = lottoParser.parseBonusNumber(rawBonusNumber);

        return new WinningLottoRequest(parsedWinningNumbers, parsedBonusNumber);
    }

    public void printLottoRankResults(List<LottoRankResponse> lottoRankResponses) {
        outputView.printLottoRankResults(lottoRankResponses);
    }

    public void printProfitRate(double profitRate) {
        outputView.printProfitRate(profitRate);
    }
}
