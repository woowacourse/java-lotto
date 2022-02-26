package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.util.InputValidator;
import lotto.util.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        int amount = inputAmount();
        Lottos lottos = createLottos(calculateLottoCount(amount));
        ResultView.printResult(lottos);

        WinningLotto winningLotto = createWinningLotto();

        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }

    private int inputAmount() {
        try {
            return InputValidator.validatePurchaseAmount(InputView.inputPurchaseAmount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputAmount();
        }
    }

    private int calculateLottoCount(int amount) {
        return amount / Lotto.LOTTO_PRICE;
    }

    private Lottos createLottos(int lottoCount) {
        return new Lottos(createLotto(lottoCount));
    }

    private List<Lotto> createLotto(int lottoCount) {
        return IntStream.range(0, lottoCount)
            .mapToObj(i -> RandomLottoGenerator.generate())
            .collect(Collectors.toList());
    }

    private WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
        }
    }
}
