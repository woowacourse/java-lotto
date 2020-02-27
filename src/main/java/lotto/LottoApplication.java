package lotto;

import lotto.domain.Customer;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoManager;
import lotto.domain.WinLotto;
import lotto.util.StringUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        int inputMoney = StringUtils.ToInt(InputView.inputMoney());
        int inputUserLottoCount = StringUtils.ToInt(InputView.inputUserLottoCount());
        String userLottoNumbers = InputView.inputUserLotto(inputUserLottoCount);
        Customer customer = new Customer(inputMoney, inputUserLottoCount, userLottoNumbers);

//        LottoGame lottoGame = new LottoGame(customer, new LottoGeneratorManual());
//        List<Lotto> manualLotto = lottoGame.lottoGenerate();
//
//        lottoGame.setLottoGenerator(new LottoGeneratorAuto());
//        List<Lotto> autoLotto = lottoGame.lottoGenerate();

        List<Lotto> lotteries = LottoFactory.createLotteries(customer, userLottoNumbers);
        OutputView.printLotteries(lotteries, customer);

        WinLotto winLotto = createWinLotto();
        LottoManager lottoManager = new LottoManager(lotteries, winLotto);
        lottoManager.checkLotto();
        OutputView.printResult(customer, lottoManager);
    }

    private static WinLotto createWinLotto() {
        String[] inputWinNumbers = StringUtils.splitByComma(InputView.inputWinNumber());
        return new WinLotto(inputWinNumbers, InputView.inputBonusBall());
    }
}
