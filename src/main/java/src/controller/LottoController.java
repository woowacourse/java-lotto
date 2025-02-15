package src.controller;

import java.util.List;
import src.model.LottoMachine;
import src.model.LottoPrize;
import src.model.lotto.Lotto;
import src.model.lotto.generator.DefaultNumberGenerator;
import src.model.lotto.generator.NumberGenerator;
import src.model.winning_lotto.WinningLotto;
import src.view.input.ConsoleInputView;
import src.view.input.InputView;
import src.view.output.ConsoleOutputView;
import src.view.output.LottoPrizeResponse;
import src.view.output.LottoResponse;
import src.view.output.OutputView;

public class LottoController {

    private final InputView inputView = new ConsoleInputView();
    private final OutputView outputView = new ConsoleOutputView();
    private final NumberGenerator numberGenerator = new DefaultNumberGenerator();
    private final LottoMachine lottoMachine = new LottoMachine(numberGenerator);

    public void run() {
        int purchaseMoney = getPurchaseMoney();
        List<Lotto> lottos = lottoMachine.issueLottos(purchaseMoney);
        printPurchasedLottos(lottos);
        WinningLotto winningLotto = getWinningLotto();
        List<LottoPrize> lottoPrizes = lottoMachine.getLottoResults(lottos, winningLotto);

        double profitRate = lottoMachine.calculateProfitRate(lottoPrizes, purchaseMoney);

        List<LottoPrizeResponse> lottoPrizeResponses = lottoPrizes.stream().map(LottoPrizeResponse::from).toList();
        outputView.printLottoResults(lottoPrizeResponses, profitRate);
    }

    private int getPurchaseMoney() {
        outputView.printInputPurchaseMoneyMessage();
        return inputView.inputPurchaseMoney();
    }

    private void printPurchasedLottos(List<Lotto> lottos) {
        List<LottoResponse> lottoResponses = lottos.stream().map(LottoResponse::new).toList();
        outputView.printPurchasedLottos(lottoResponses);
    }

    private WinningLotto getWinningLotto() {
        List<Integer> winningLottoNumbers = getWinningLottoNumbers();
        int bonusNumber = getBonusNumber();

        return WinningLotto.of(winningLottoNumbers, bonusNumber);
    }

    private List<Integer> getWinningLottoNumbers() {
        outputView.printInputWinningLottoNumbers();
        return inputView.inputWinningLottoNumbers();
    }

    private int getBonusNumber() {
        outputView.printInputBonusNumber();
        return inputView.inputBonusNumber();
    }
}
