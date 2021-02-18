package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.domain.Money;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottogenerator.RandomLottoGenerator;
import lotto.exception.LessThanLottoPriceException;
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
        // Lottos 구매한_로또들 = LottoStore.buyLotto(Money);
        // WinningLotto 당첨_번호 = inputWinningLotto();

        // LottoResult 로또_결과 = Lottos.match(당첨_번호);
        // 로또 결과 출력
    }

    private Money inputPurchaseAmount() {
        try {
            OutputView.printPurchaseAmountGuideMessage();
            int purchaseAmountValue = Integer.parseInt(inputView.inputPurchaseAmount());
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
}


