package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.lotto.Lottos;
import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;
import lotto.domain.user.PurchaseLottoCount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void printLottos(final Lottos lottos, final PurchaseLottoCount purchaseLottoCount) {
        OutputView.printLottos(lottos, purchaseLottoCount.getManualLottoCount(), purchaseLottoCount.getAutoLottoCount());
    }

    public Lottos createAutoLottos(final PurchaseLottoCount purchaseLottoCount) {
        int inputMoney = Money.getMoneyByCount(purchaseLottoCount.getAutoLottoCount());
        final Money money = new Money(inputMoney);
        return new Lottos(money.getCount());
    }

    public LottoWinningNumbers createLottoWinningNumbers() {
        try {
            String numbers = inputLottoWinningNumbers();
            Lotto lotto = createLottoByNumbers(numbers);
            LottoNumber bonusNumber = new LottoNumber(inputBonusNumber());
            return new LottoWinningNumbers(lotto, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createLottoWinningNumbers();
        }
    }

    private String inputLottoWinningNumbers() {
        try {
            return removeBlank(InputView.inputLottoWinningNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputLottoWinningNumbers();
        }
    }

    public Lotto createLottoByNumbers(final String numbers) {
        final List<Integer> lotto = Arrays.stream(numbers.split(","))
                .map(this::removeBlank)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new Lotto(lotto);
    }

    private String removeBlank(final String value) {
        return value.replace(" ", "");
    }

    public String inputBonusNumber() {
        return InputView.inputBonusNumber();
    }

    public LottoResult calculateRanks(final Lottos lottos, final LottoWinningNumbers numbers) {
        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : lottos.getLottos()) {
            lottoResult.calculateWinning(lotto, numbers.getWinningLotto(), numbers.getBonusNumber());
        }

        return lottoResult;
    }

    public void printWinningResult(final LottoResult lottoResult) {
        OutputView.printWinningResult(lottoResult);
    }

    public Lottos createManualLottos(final PurchaseLottoCount purchaseLottoCount) {
        try {
            Lottos lottos = new Lottos();
            OutputView.printManualLotto();
            addManualLotto(purchaseLottoCount.getManualLottoCount(), lottos);
            return lottos;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createManualLottos(purchaseLottoCount);
        }
    }

    private void addManualLotto(final int purchaseLottoCount, final Lottos lottos) {
        for (int i = 0; i < purchaseLottoCount; i++) {
            lottos.addLotto(createLottoByNumbers(InputView.inputManualLotto()));
        }
    }

    public Lottos combineLottos(final Lottos firstLottos, final Lottos secondLottos) {
        Lottos totalLottos = new Lottos();
        totalLottos.addLottos(firstLottos.getLottos());
        totalLottos.addLottos(secondLottos.getLottos());
        return totalLottos;
    }
}
