package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoStore;
import domain.Lottos;
import domain.Money;
import domain.Number;
import domain.WinningLotto;
import domain.WinningResult;
import java.util.Arrays;
import java.util.List;
import view.InputValidator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final InputValidator inputValidator;
    private final OutputView outputView;

    public LottoController(InputView inputView, InputValidator inputValidator, OutputView outputView) {
        this.inputView = inputView;
        this.inputValidator = inputValidator;
        this.outputView = outputView;
    }

    public void start() {
        try {
            Money purchaseLottoMoney = inputMoney();
            Lottos purchasedLottos = purchaseLottos(purchaseLottoMoney);
            outputView.printPurchaseLottos(purchasedLottos);

            WinningLotto winningLotto = inputWinningLotto();
            WinningResult winningResult = purchasedLottos.calculateWinning(winningLotto);
            outputView.printWinningResult(winningResult);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private Money inputMoney() {
        String rawMoney = inputView.inputMoney();
        inputValidator.validateInputMoney(rawMoney);
        return new Money(rawMoney);
    }

    private Lottos purchaseLottos(Money purchaseLottoMoney) {
        LottoStore lottoStore = new LottoStore(new LottoMachine());
        return lottoStore.buy(purchaseLottoMoney);
    }

    private WinningLotto inputWinningLotto() {
        Lotto winningNumbers = inputWinningNumbers();

        Number bonusNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto inputWinningNumbers() {
        String rawWinningNumbers = inputView.inputWinningNumbers();
        inputValidator.validateWinningNumber(rawWinningNumbers);
        List<Number> numbers = Arrays.stream(rawWinningNumbers.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .map(Number::new)
                .toList();
        return new Lotto(numbers);
    }

    private Number inputBonusNumber() {
        String rawBonusNumber = inputView.inputBonusNumber();
        inputValidator.validateNotStringNumber(rawBonusNumber);
        return new Number(Integer.parseInt(rawBonusNumber));
    }
}
