package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoManager;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.util.StringUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoApplication {
    private static final int MIN_USER_LOTTO_COUNT = 0;

    public static void main(String[] args) {
        int inputMoney = StringUtils.ToInt(InputView.inputMoney());
        int inputUserLottoCount = StringUtils.ToInt(InputView.inputUserLottoCount());
        Money money = new Money(inputMoney, inputUserLottoCount);
        String userLottoNumbers = setUserLottoNumbers(money);

        List<Lotto> lotteries = LottoFactory.createLotteries(money, userLottoNumbers);
        OutputView.printLotteries(lotteries, money.getUserLottoCount());

        WinLotto winLotto = setWinLotto();
        LottoManager lottoManager = new LottoManager(lotteries, winLotto);
        lottoManager.checkLotto();
        OutputView.printResult(money, lottoManager);
    }

    private static WinLotto setWinLotto() {
        String[] inputWinNumbers = StringUtils.splitNumber(InputView.inputWinNumber());
        return new WinLotto(inputWinNumbers, InputView.inputBonusBall());
    }

    private static String setUserLottoNumbers(Money money) {
        validate(money);
        String inputUserLottoNumbers = "";
        if (money.getUserLottoCount() > MIN_USER_LOTTO_COUNT) {
            inputUserLottoNumbers = InputView.inputUserLotto(money.getUserLottoCount());
        }
        return inputUserLottoNumbers;
    }

    private static void validate(Money money) {
        if (money == null) {
            throw new IllegalArgumentException();
        }
    }
}
