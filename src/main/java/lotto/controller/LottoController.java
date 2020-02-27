package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoParser;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.LottosGenerator;
import lotto.domain.lottomoney.InvalidLottoMoneyException;
import lotto.domain.lottomoney.LottoMoney;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.WinningLotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

public class LottoController {

    private static final String COUNT_OF_MANUAL_IS_WRONG_RANGE_MESSAGE = "수동 로또 갯수는 0보다 작거나 구매한 로또 갯수보다 클 수 없습니다.";
    private static final String COUNT_OF_MANUAL_IS_WRONG_TYPE_MESSAGE = "숫자를 입력해주세요";

    public void run() {
        LottoMoney inputLottoMoney = receiveInputMoney();
        int countOfLotto = inputLottoMoney.calculateCountOfLotto();
        int countOfManualLotto = receiveCountOfManualLotto(countOfLotto);
        int countOfAutoLotto = countOfLotto - countOfManualLotto;
        Lottos manualLottos = receiveManualLotto(countOfManualLotto);
        printPurchaseCompleteMessage(countOfManualLotto, countOfAutoLotto);
        Lottos autoLottos = LottosGenerator.generate(countOfAutoLotto);
        printPurchasedLotto(manualLottos);
        printPurchasedLotto(autoLottos);

        WinningLotto winningLotto = receiveWinningLotto();
        LottoWinningResult winningResult = new LottoWinningResult(autoLottos, winningLotto);
        printWinningResult(winningResult.getLottoRankCount());
        printWinningRatio(winningResult.calculateWinningRatio(inputLottoMoney));
    }

    private static LottoMoney receiveInputMoney() {
        try {
            return new LottoMoney(inputMoney());
        } catch (InvalidLottoMoneyException ime) {
            printExceptionMessage(ime.getMessage());
            return receiveInputMoney();
        }
    }

    private static int receiveCountOfManualLotto(int numberOfLotto) {
        int numberOfManualLotto;
        try {
            numberOfManualLotto = Integer.parseInt(inputCountOfManualLotto());
        } catch (IllegalArgumentException e) {
            printExceptionMessage(COUNT_OF_MANUAL_IS_WRONG_TYPE_MESSAGE);
            return receiveCountOfManualLotto(numberOfLotto);
        }
        if (numberOfManualLotto < 0 || numberOfManualLotto > numberOfLotto) {
            throw new IllegalArgumentException(COUNT_OF_MANUAL_IS_WRONG_RANGE_MESSAGE);
        }
        return numberOfManualLotto;
    }

    private static Lottos receiveManualLotto(int countOfManualLotto) {
        List<Lotto> manualLottos = new ArrayList<>();
        while (countOfManualLotto > 0) {
            manualLottos.add(new Lotto(LottoParser.parser(inputManualLottoNumber())));
            countOfManualLotto--;
        }
        return new Lottos(manualLottos);
    }

    private static WinningLotto receiveWinningLotto() {
        Lotto inputWinningLotto = new Lotto(LottoParser.parser(inputWinningLottoNumber()));
        LottoNumber inputBonusNumber = LottoNumber.valueOf(inputBonusLottoNumber());
        return new WinningLotto(inputWinningLotto, inputBonusNumber);
    }
}
