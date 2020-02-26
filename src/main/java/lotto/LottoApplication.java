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
    private static final String ERROR_MESSAGE_OVER_COUNT = "구입 가능한 수보다 큰 수를 입력하였습니다.";
    private static final int MIN_USER_LOTTO_COUNT = 0;

    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        int userLottoCount = StringUtils.ToInt(InputView.inputUserLottoCount());
        String userLottoNumbers = setUserLottoNumbers(userLottoCount, money);

        List<Lotto> lotteries = LottoFactory.createLotteries(money, userLottoNumbers);
        OutputView.printLotteries(lotteries, userLottoCount);

        WinLotto winLotto = setWinLotto();
        LottoManager lottoManager = new LottoManager(lotteries, winLotto);
        lottoManager.checkLotto();
        OutputView.printResult(money, lottoManager);
    }

    private static WinLotto setWinLotto() {
        String[] inputWinNumbers = StringUtils.splitNumber(InputView.inputWinNumber());
        return new WinLotto(inputWinNumbers, InputView.inputBonusBall());
    }

    private static String setUserLottoNumbers(int userLottoCount, Money money) {
        if (userLottoCount > money.findBuyAmount()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_OVER_COUNT);
        }

        String inputUserLottoNumbers = "";
        if (userLottoCount > MIN_USER_LOTTO_COUNT) {
            inputUserLottoNumbers = InputView.inputUserLotto(userLottoCount);
        }
        return inputUserLottoNumbers;
    }
}
