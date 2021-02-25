package lotto.controller;

import lotto.domain.*;
import lotto.utils.AutoLottoGenerator;
import lotto.utils.FixedLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

import static lotto.domain.LottoQuantity.ZERO_COUNT;

public class LottoGameController {

    private final LottoGame lottoGame = new LottoGame();

    public void run() {
        Money totalMoney = InputView.askMoney();
        LottoQuantity lottoQuantity = createLottoQuantity(totalMoney);
        buyLottos(lottoQuantity);

        LottoNumbers lastWinningLottoNumbers = InputView.askLastWinningLottoNumbers();
        LottoNumber bonusNumber = createBonusNumber(lastWinningLottoNumbers);
        WinningLotto lastWinningLotto = lottoGame.createWinningLotto(lastWinningLottoNumbers, bonusNumber);

        LottoGameResult lottoGameResult = lottoGame.calculateLottoGameResult(lastWinningLotto);
        OutputView.printLottoGameResult(lottoGameResult);
    }

    private LottoQuantity createLottoQuantity(Money totalMoney) {
        try {
            return lottoGame.createLottoQuantity(totalMoney, InputView.askFixedLottoQuantity());
        } catch (Exception e) {
            OutputView.printError(e);
            return createLottoQuantity(totalMoney);
        }
    }

    private void buyLottos(LottoQuantity lottoQuantity) {
        if (lottoQuantity.getFixedLottoQuantity() > ZERO_COUNT) {
            buyFixedLotto(lottoQuantity);
        }
        if (lottoQuantity.getAutoLottoQuantity() > ZERO_COUNT) {
            buyAutoLotto(lottoQuantity);
        }
        OutputView.printEachLotto(lottoGame.myLottos());
    }

    private void buyFixedLotto(LottoQuantity lottoQuantity) {
        List<LottoNumbers> fixedLottoNumbersBundle = createLottoNumbersBundle(lottoQuantity);
        if (lottoQuantity.getFixedLottoQuantity() != ZERO_COUNT) {
            lottoGame.buyLottos(lottoQuantity.calculateFixedLottoPrice(),
                    new FixedLottoGenerator(fixedLottoNumbersBundle));
        }
    }

    private void buyAutoLotto(LottoQuantity lottoQuantity) {
        lottoGame.buyLottos(lottoQuantity.calculateAutoLottoPrice(), new AutoLottoGenerator());
    }

    private List<LottoNumbers> createLottoNumbersBundle(LottoQuantity lottoQuantity) {
        try {
            return InputView.askFixLottoNumbersBundle(lottoQuantity.getFixedLottoQuantity());
        } catch (Exception e) {
            OutputView.printError(e);
            return createLottoNumbersBundle(lottoQuantity);
        }
    }

    private LottoNumber createBonusNumber(LottoNumbers lottoNumbers) {
        LottoNumber bonusNumber = InputView.askBonusNumber();
        try {
            lottoNumbers.checkBonusNumber(bonusNumber);
            return bonusNumber;
        } catch (Exception e) {
            OutputView.printError(e);
            return createBonusNumber(lottoNumbers);
        }
    }
}
