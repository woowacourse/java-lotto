package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoStore;
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
            String rawMoney = inputView.inputMoney();
            inputValidator.validateInputMoney(rawMoney);
            Money purchaseLottoMoney = new Money(rawMoney);
            LottoStore lottoStore = new LottoStore(new LottoMachine());
            List<Lotto> purchaseLottos = lottoStore.buy(purchaseLottoMoney);
            outputView.printPurchaseLottos(purchaseLottos);

            String rawWinningNumbers = inputView.inputWinningNumbers();
            inputValidator.validateWinningNumber(rawWinningNumbers);
            List<Number> numbers = Arrays.stream(rawWinningNumbers.split(","))
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .map(Number::new)
                    .toList();
            Lotto winningNumbers = new Lotto(numbers);
            String rawBonusNumber = inputView.inputBonusNumber();
            inputValidator.validateNotStringNumber(rawBonusNumber);
            Number bonusNumber = new Number(Integer.parseInt(rawBonusNumber));
            WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
            WinningResult winningResult = winningLotto.calculateWinning(purchaseLottos);
            outputView.printWinningResult(winningResult);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }
}
