package lotto.controller;

import java.util.List;
import lotto.model.LottoMachine;
import lotto.model.LottoPrize;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.generator.DefaultNumberGenerator;
import lotto.model.lotto.generator.NumberGenerator;
import lotto.model.winning_lotto.WinningLotto;
import lotto.view.input.ConsoleInputView;
import lotto.view.input.InputView;
import lotto.view.output.ConsoleOutputView;
import lotto.view.output.LottoPrizeResponse;
import lotto.view.output.LottoResponse;
import lotto.view.output.OutputView;

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

        List<LottoPrize> lottoPrizes = lottoMachine.calculateLottoResults(lottos, winningLotto);
        List<LottoPrizeResponse> lottoPrizeResponses = lottoPrizes.stream().map(LottoPrizeResponse::from).toList();

        outputView.printLottoResults(lottoPrizeResponses, purchaseMoney);
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
