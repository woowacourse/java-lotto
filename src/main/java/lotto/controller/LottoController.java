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
import lotto.domain.lotto.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(Scanner scanner) {
        this.inputView = new InputView(scanner);
    }

    public void play() {
        OutputView.inputMoney();
        Money money = inputView.inputMoney();
        LottoStore lottoStore = new LottoStore();
        Lottos purchasedLottos = lottoStore.buyLottos(money);
        OutputView.numPurchasedLotto(purchasedLottos.getNumLotto());
        OutputView.lottosPrint(purchasedLottos);

        List<Integer> winningNumbers = createWinningNumbers();
        Integer bonusNumber = createBonusNUmber();

        WinningLotto winningLotto = WinningLotto.generatedBy(Lotto.generatedBy(winningNumbers),
            LottoNumber.valueOf(bonusNumber));

        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : purchasedLottos.getLottos()) {
            lottoResult.add(lotto, winningLotto);
        }

        OutputView.totalWinning();
        OutputView.numMatchPrint(lottoResult);
    }


    private List<Integer> createWinningNumbers() {
        OutputView.inputWinningNumber();
        String winningNumbers = inputView.inputWinningNumbers();
        List<Integer> lottoNumbers = Arrays
            .stream(winningNumbers
                .split(", "))
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


