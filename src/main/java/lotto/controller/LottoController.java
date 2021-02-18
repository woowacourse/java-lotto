package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottogenerator.RandomLottoGenerator;
import lotto.exception.LessThanLottoPriceException;
import lotto.model.LottoResult;
import lotto.model.Money;
import lotto.utils.InputValidationUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(Scanner scanner) {
        this.inputView = new InputView(scanner);
    }

    public void play() {
        Money purchaseAmount = inputPurchaseAmount();
        Lottos purchasedLottos = buyLotto(purchaseAmount);
        OutputView.printLottos(purchasedLottos);

        Lotto winningLottoNumber = inputWinningLotto();
        int bonus = inputWinningBonus();
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonus);
        LottoResult lottoResult = purchasedLottos.match(winningLotto);
        OutputView.printLottoResult(lottoResult);
    }

    private Money inputPurchaseAmount() {
        try {
            OutputView.printPurchaseAmountGuideMessage();
            int purchaseAmountValue = Integer.parseInt(inputView.inputValue());
            InputValidationUtils.validatePurchaseAmount(purchaseAmountValue);
            return Money.priceOf(purchaseAmountValue);
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmount();
        }
    }

    private Lottos buyLotto(Money purchaseAmount) {
        if (purchaseAmount.isLessThan(1000)) {
            throw new LessThanLottoPriceException();
        }
        int numOfAvailableLotto = purchaseAmount.getPrice() / 1000;
        List<Lotto> availableLotto = new ArrayList<>();
        for (int i = 0; i < numOfAvailableLotto; i++) {
            availableLotto.add(Lotto.generatedBy(new RandomLottoGenerator()));
        }
        return new Lottos(availableLotto);
    }

    private Lotto inputWinningLotto() {
        try {
            OutputView.printWinningLottoGuideMessage();
            List<Integer> winningLottoNumbers = inputView.inputWinningLottoNumbers()
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            return Lotto.of(winningLottoNumbers);
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputWinningLotto();
        }
    }

    private int inputWinningBonus() {
        try {
            OutputView.printWinningLottoBonusGuideMessage();
            int bonusValue = Integer.parseInt(inputView.inputValue());
            InputValidationUtils.validateWinningBonus(bonusValue);
            return bonusValue;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputWinningBonus();
        }
    }
}


