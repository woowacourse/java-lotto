package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.LottoCount;
import lotto.model.LottoMachine;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    private LottoGenerator lottoGenerator;

    public LottoController(final LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money money = inputMoney();

        LottoCount lottoCount = inputLottoCount(money);

        LottoMachine lottoMachine = new LottoMachine(lottoGenerator);

        Lottos boughtLottos = lottoMachine.buy(money, lottoCount, inputManualLottos(lottoCount.getManualLottoCount()));

        ResultView.printBuyingLottosResult(lottoCount, boughtLottos);

        WinningLotto winningLotto = makeWinningLotto();

        lottoMachine.calculateResult(winningLotto);

        ResultView.printTotalRankResult(lottoMachine);
    }

    private Money inputMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }

    private LottoCount inputLottoCount(final Money money) {
        try {
            return new LottoCount(InputView.inputManualLottoCount(), money);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputLottoCount(money);
        }
    }

    private List<Lotto> inputManualLottos(final int count) {
        try {
            List<List<Integer>> lottos = InputView.inputManualNumbers(count);
            return lottos.stream()
                    .map(this::makeLottoNumbers)
                    .map(Lotto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputManualLottos(count);
        }
    }

    private LottoNumbers makeLottoNumbers(final List<Integer> numbers) {
        return new LottoNumbers(numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private WinningLotto makeWinningLotto() {
        try {
            LottoNumbers lottoNumbers = makeLottoNumbers(InputView.inputWinningNumbers());
            LottoNumber lottoBonusNumber = new LottoNumber(InputView.inputBonusNumber());
            return new WinningLotto(lottoNumbers, lottoBonusNumber);
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            return makeWinningLotto();
        }
    }
}
