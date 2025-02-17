package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Prize;
import domain.WinningLotto;
import domain.properties.LottoProperties;
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
        final int purchaseAmount = readPurchaseAmount();
        Lottos lottos = createLottos(purchaseAmount);

        List<LottoNumber> winningLottoNumbers = readWinningNumbers();
        LottoNumber bonusLottoNumber = readBonusNumber();

        final WinningLotto winningLotto = WinningLotto.of(Lotto.of(winningLottoNumbers), bonusLottoNumber);
        List<Prize> prizes = winningLotto.calculatePrizes(lottos);

        outputView.printLottoResult(prizes,
                Prize.calculateEarningRate(prizes, lottos.getQuantity() * LottoProperties.PRICE)
        );
    }

    private int readPurchaseAmount() throws IOException {
        String rawPurchaseAmount = inputView.readPurchaseAmount();
        InputValidator.validateInteger(rawPurchaseAmount);
        final int purchaseAmount = Integer.parseInt(rawPurchaseAmount);
        return purchaseAmount;
    }

    private Lottos createLottos(final int purchaseAmount) {
        Lottos lottos = Lottos.ofSize(purchaseAmount / LottoProperties.PRICE);
        outputView.printPurchasedLottos(lottos);
        return lottos;
    }

    private List<LottoNumber> readWinningNumbers() throws IOException {
        String rawWinningNumber = inputView.readWinningNumber();
        final List<String> rawWinningNumbers = Arrays.stream(rawWinningNumber.split(",")).map(String::trim).toList();
        InputValidator.validateElements(rawWinningNumbers);
        final List<Integer> winningNumbers = rawWinningNumbers.stream().map(Integer::parseInt).toList();

        return winningNumbers.stream()
                .map(LottoNumber::of)
                .toList();
    }

    private LottoNumber readBonusNumber() throws IOException {
        final String rawBonusNumber = inputView.readBonusNumber();
        InputValidator.validateInteger(rawBonusNumber);
        final int bonusNumber = Integer.parseInt(rawBonusNumber);
        return LottoNumber.of(bonusNumber);
    }
}
