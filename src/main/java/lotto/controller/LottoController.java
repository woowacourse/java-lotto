package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoNumber;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        LottoMachine lottoMachine = createLottoMachine();
        Lottos lottos = createLottos(lottoMachine);
        ResultView.printResult(lottos, lottoMachine);

        WinningLotto winningLotto = createWinningLotto();
        ResultView.printTotalResult(lottos.checkRank(winningLotto));
    }

    private LottoMachine createLottoMachine() {
        try {
            return new LottoMachine(inputMoney().purchase(Lotto.LOTTO_PRICE), InputView.inputManualLottoCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createLottoMachine();
        }
    }

    private Money inputMoney() {
        return new Money(InputView.inputPurchaseAmount());
    }

    private Lottos createLottos(LottoMachine lottoMachine) {
        return new Lottos(lottoMachine.createLottos(createManualLottos(lottoMachine)));
    }

    private List<Lotto> createManualLottos(LottoMachine lottoMachine) {
        try {
            return InputView.inputManualLottos(lottoMachine).stream()
                .map(this::convertToLotto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createManualLottos(lottoMachine);
        }
    }

    private WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(convertToLotto(InputView.inputWinningLotto()), inputBonusNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
        }
    }

    private Lotto convertToLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
            .sorted()
            .map(LottoNumber::new)
            .collect(Collectors.toList()));
    }

    private LottoNumber inputBonusNumber() {
        return new LottoNumber(InputView.inputBonusNumber());
    }
}
