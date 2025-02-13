package controller;

import domain.Lotto;
import domain.Lottos;
import domain.Prize;
import domain.WinningLotto;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import util.InputValidator;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() throws IOException {

        // 1. 구매 금액 입력받기
        String rawPurchaseAmount = inputView.readPurchaseAmount();
        InputValidator.validateInteger(rawPurchaseAmount);
        final int purchaseAmount = Integer.parseInt(rawPurchaseAmount);

        // 2. Lottos 객체 생성하기
        Lottos lottos = Lottos.ofSize(purchaseAmount / 1000);
        outputView.printPurchasedLottos(lottos);

        //3. 당첨 번호 입력받기
        String rawWinningNumber = inputView.readWinningNumber();
        final List<String> rawWinningNumbers = Arrays.stream(rawWinningNumber.split(",")).map(String::trim).toList();
        final List<Integer> winningNumbers = rawWinningNumbers.stream().map(Integer::parseInt).toList();

        //4. 보너스 번호 입력받기
        final String rawBonusNumber = inputView.readBonusNumber();
        final int bonusNumber = Integer.parseInt(rawBonusNumber);

        //5. WinningLotto 객체 생성하기
        final WinningLotto winningLotto = WinningLotto.of(Lotto.of(winningNumbers), bonusNumber);

        // 6. 계산하기
        List<Prize> prizes = winningLotto.calculatePrizes(lottos);

        outputView.printLottoResult(prizes, Prize.calculateEarningRate(prizes, lottos.getQuantity() * 1000));

    }
}
