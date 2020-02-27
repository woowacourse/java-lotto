package lotto;

import lotto.domain.Customer;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoGeneratorAuto;
import lotto.domain.LottoGeneratorManual;
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

        LottoGame manualLottoGame = new LottoGame(customer, new LottoGeneratorManual());
        List<Lotto> lottos = manualLottoGame.lottoGenerate();
        LottoGame AutoLottoGame = new LottoGame(customer, new LottoGeneratorAuto());
        AutoLottoGame.lottoGenerate().stream().forEach(x -> lottos.add(x));

        OutputView.printLotteries(lottos, customer);

        WinLotto winLotto = createWinLotto();
        LottoManager lottoManager = new LottoManager(lottos, winLotto);
        lottoManager.checkLotto();
        OutputView.printResult(customer, lottoManager);
    }

    private static WinLotto createWinLotto() {
        String[] inputWinNumbers = StringUtils.splitByComma(InputView.inputWinNumber());
        return new WinLotto(inputWinNumbers, InputView.inputBonusBall());
    }
}
