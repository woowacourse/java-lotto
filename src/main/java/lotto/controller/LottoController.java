package lotto.controller;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.ManualLotto;
import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;
import lotto.exception.InvalidException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void printLottos(final Lottos lottos, int purchaseLottoCount, int remainPurchaseLottoCount) {
        OutputView.printLottos(lottos, purchaseLottoCount, remainPurchaseLottoCount);
    }

    public Lottos inputLottoMoney(final int inputMoney) {
        Money money = new Money(inputMoney);
        return new Lottos(money.getCount());
    }

    public LottoWinningNumbers createLottoWinningNumbers() {
        try {
            String value = inputLottoWinningNumbers();
            int bonusNumber = inputBonusNumber();
            return new LottoWinningNumbers(value, bonusNumber);
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

    private String removeBlank(final String value) {
        return value.replace(" ", "");
    }

    public int inputBonusNumber() {
        String bonusNumber = InputView.inputBonusNumber();
        checkValidateInt(bonusNumber);
        return Integer.parseInt(bonusNumber);
    }

    private static void checkValidateInt(final String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(InvalidException.ERROR_WRONG_INPUT_MONEY);
        }
    }

    public LottoResult calculateRanks(final Lottos lottos, final LottoWinningNumbers lottoWinningNumbers) {
        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : lottos.getLottos()) {
            lottoResult.calculateWinning(lottoWinningNumbers.getWinningLotto(), lottoWinningNumbers.getBonusNumber(),
                    lotto);
        }

        return lottoResult;
    }

    public void printWinningResult(final LottoResult lottoResult) {
        OutputView.printWinningResult(lottoResult);
    }

    public ManualLotto inputManualLotto(int purchaseLottoCount) {
        ManualLotto manualLotto = new ManualLotto();
        OutputView.printManualLotto();
        addManualLotto(purchaseLottoCount, manualLotto);
        return manualLotto;
    }

    private void addManualLotto(int purchaseLottoCount, ManualLotto manualLotto) {
        for (int i = 0; i < purchaseLottoCount; i++) {
            manualLotto.add(InputView.inputManualLotto());
        }
    }

    public Lottos addLottos(Lottos lottos, List<Lotto> addLottos) {
        return lottos.addLottos(addLottos);
    }
}
