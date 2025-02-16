package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Prize;
import lotto.domain.WinningLotto;
import lotto.util.NumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final int purchaseAmount = requestPurchaseAmount();
        Lottos lottos = createLottos(purchaseAmount);

        final List<Integer> winningNumbers = requestWinningNumbers();
        final int bonusNumber = requestBonusNumber();
        final WinningLotto winningLotto = WinningLotto.of(Lotto.of(winningNumbers), bonusNumber);

        List<Prize> prizes = lottos.calculatePrizes(winningLotto);
        double earningRate = lottos.calculateEarningRate(prizes);

        outputView.printLottoResult(prizes, earningRate);
        inputView.close();
    }

    private int requestPurchaseAmount() {
        String rawPurchaseAmount = inputView.read("구입금액을 입력해 주세요.");
        return NumberParser.parse(rawPurchaseAmount);
    }

    private Lottos createLottos(final int purchaseAmount) {
        Lottos lottos = Lottos.ofSize(purchaseAmount / 1000);
        outputView.printPurchasedLottos(lottos);
        return lottos;
    }

    private List<Integer> requestWinningNumbers() {
        String rawWinningNumber = inputView.read("지난 주 당첨 번호를 입력해 주세요.");
        return NumberParser.parseFromCSV(rawWinningNumber);
    }

    private int requestBonusNumber() {
        final String rawBonusNumber = inputView.read("보너스 볼을 입력해 주세요.");
        return NumberParser.parse(rawBonusNumber);
    }
}
