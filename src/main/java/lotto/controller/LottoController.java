package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.Money;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoStore;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.ManualLotto;
import lotto.domain.lotto.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public static final String REGEX = ", ";
    private final InputView inputView;

    public LottoController(Scanner scanner) {
        this.inputView = new InputView(scanner);
    }

    public void play() {
        try {
            Lottos purchasedLottos = createLottos();
            WinningLotto winningLotto = createWinningLotto();
            compareLotto(purchasedLottos, winningLotto);
        } catch (Exception error) {
            OutputView.errorPrint(error);
            play();
        }
    }

    private WinningLotto createWinningLotto() {
        List<Integer> winningNumbers = createWinningNumbers();
        Integer bonusNumber = createBonusNUmber();
        return WinningLotto.generatedBy(Lotto.generatedBy(winningNumbers),
            LottoNumber.valueOf(bonusNumber));
    }

    private void compareLotto(Lottos purchasedLottos, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();

        purchasedLottos.check(lottoResult, winningLotto);

        OutputView.totalWinning();
        OutputView.numMatchPrint(lottoResult);
        OutputView.profitRatePrint(lottoResult);
    }

    private Lottos createLottos() {
        Money money = startMoney();
        ManualLotto manualLotto = buyManualLotto();

        LottoStore lottoStore = new LottoStore();
        Lottos purchasedLottos = lottoStore.buyLottos(money);

        OutputView.numPurchasedLotto(purchasedLottos.size());
        OutputView.lottosPrint(purchasedLottos);

        return purchasedLottos;

    }

    private ManualLotto buyManualLotto() {
        OutputView.inputNumManualLotto();
        return inputView.inputNumManualLotto();
    }

    private Money startMoney() {
        OutputView.inputMoney();
        return inputView.inputMoney();
    }


    private List<Integer> createWinningNumbers() {
        OutputView.inputWinningNumber();
        String winningNumbers = inputView.inputWinningNumbers();
        List<Integer> lottoNumbers = Arrays
            .stream(winningNumbers
                .split(REGEX))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        return lottoNumbers;
    }

    private Integer createBonusNUmber() {
        OutputView.inputBonus();
        String bonusNumber = inputView.inputBonusNumber();
        return Integer.parseInt(bonusNumber);
    }
}


