package lotto.controller;

import lotto.domain.*;
import lotto.utils.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.Quantity.ZERO_COUNT;

public class LottoGameController {

    private LottoGame lottoGame;

    public void run() {
        startLottoGame();
        playLottoGame();
    }

    private void startLottoGame() {
        Money price = createMoney();
        Quantity manualQuantity = createManualLottoQuantity();
        Quantity autoQuantity = createAutoLottoQuantity(price, manualQuantity);

        LottosGenerator lottosGenerator = createGenerator(manualQuantity, autoQuantity);
        lottoGame = new LottoGame(lottosGenerator);
    }

    private void playLottoGame() {
        Lottos myLottos = lottoGame.createLottos();
        OutputView.printEachLotto(myLottos);

        LottoNumbers winningNumbers = createWinningNumbers();
        LottoNumber bonusNumber = createBonusNumber();
        WinningLotto winningLotto = createWinningLotto(winningNumbers, bonusNumber);

        LottoGameResult lottoGameResult = lottoGame.calculateYield(myLottos, winningLotto);
        OutputView.printLottoGameResult(lottoGameResult);
    }

    private Money createMoney() {
        String money = InputView.askMoney();
        try {
            return new Money(money);
        } catch (Exception e) {
            OutputView.printError(e);
            return createMoney();
        }
    }

    private Quantity createManualLottoQuantity() {
        String manualQuantity = InputView.askManualLottoQuantity();
        try {
            return new Quantity(manualQuantity);
        } catch (Exception e) {
            OutputView.printError(e);
            return createManualLottoQuantity();
        }
    }

    private Quantity createAutoLottoQuantity(Money price, Quantity manualQuantity) {
        Money manualPrice = manualQuantity.calculateQuantityPrice();
        try {
            Money autoPrice = price.calculateSubtract(manualPrice);
            return new Quantity(autoPrice);
        } catch (Exception e) {
            OutputView.printError(e);
            return createAutoLottoQuantity(createMoney(), createManualLottoQuantity());
        }
    }

    private LottosGenerator createGenerator(Quantity manualQuantity, Quantity autoQuantity) {
        List<String> manualNumbers = new ArrayList<>();
        if (manualQuantity.quantity() > ZERO_COUNT) {
            manualNumbers = createManualNumbers(manualQuantity);
        }
        try {
            return new ComplexLottosGenerator(
                    new ManualLottosGenerator(manualNumbers),
                    new AutoLottosGenerator(autoQuantity)
            );
        } catch (Exception e) {
            OutputView.printError(e);
            return createGenerator(manualQuantity, autoQuantity);
        }
    }

    private List<String> createManualNumbers(Quantity manualQuantity) {
        try {
            return InputView.askManualLottoNumbers(manualQuantity.quantity());
        } catch (Exception e) {
            OutputView.printError(e);
            return createManualNumbers(manualQuantity);
        }
    }

    private LottoNumbers createWinningNumbers() {
        String numbers = InputView.askWinningLottoNumbers();
        try {
            return LottoNumbersFactory.createLottoNumbers(numbers);
        } catch (Exception e) {
            OutputView.printError(e);
            return createWinningNumbers();
        }
    }

    private LottoNumber createBonusNumber() {
        String number = InputView.askBonusNumber();
        try {
            return new LottoNumber(number);
        } catch (Exception e) {
            OutputView.printError(e);
            return createBonusNumber();
        }
    }

    private WinningLotto createWinningLotto(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        try {
            return lottoGame.createWinningLotto(winningNumbers, bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e);
            return createWinningLotto(winningNumbers, createBonusNumber());
        }
    }
}
