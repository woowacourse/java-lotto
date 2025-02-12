package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoStore;
import domain.Money;
import domain.WinningLotto;
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
        String rawMoney = inputView.inputMoney();
        inputValidator.validateInputMoney(rawMoney);
        Money money = new Money(rawMoney);
        LottoStore lottoStore = new LottoStore(new LottoMachine());
        List<Lotto> lottos = lottoStore.buy(money);
        outputView.printPurchaseLottos(lottos);

        String rawWinningNumbers = inputView.inputWinningNumbers();
        inputValidator.validateWinningNumber(rawWinningNumbers);
        List<Integer> numbers = Arrays.stream(rawWinningNumbers.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .toList();
        Lotto winningNumbers = new Lotto(numbers);
        String rawBonusNumber = inputView.inputBonusNumber();
        int bonusNumber = Integer.parseInt(rawBonusNumber);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }
}
