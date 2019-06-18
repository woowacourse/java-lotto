package lotto;

import lotto.model.*;
import lotto.model.exceptions.IllegalCountException;
import lotto.model.exceptions.IllegalLottoNumberException;
import lotto.model.exceptions.IllegalMoneyException;
import lotto.model.exceptions.IllegalNumberCombinationException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LottoLauncher {
    private final static Logger LOGGER = Logger.getLogger(LottoLauncher.class.getName());
    private static final int STARTING_COUNT = 0;

    public static void main(String[] args) {
        Money money = generateMoney();
        ManualLottoCount manualLottoCount = generateManualLottoCount(money);
        Lottos lottos = generateLottos(money, manualLottoCount);
        OutputView.printLottos(money, manualLottoCount, lottos);

        WinningLotto winningLotto = generateWinningLotto();
        LottoResult lottoResult = new LottoResult(money, lottos.getPrizes(winningLotto));
        OutputView.printStatistics(lottoResult);
    }

    private static Lottos generateLottos(Money money, ManualLottoCount manualLottoCount) {
        try {
            List<String> manualLottos = new ArrayList<>();
            int currentCount = STARTING_COUNT;
            while (manualLottoCount.next(currentCount)) {
                manualLottos.add(InputView.askManualLottoNumbers());
                currentCount++;
            }
            return LottoService.produceLottos(money, manualLottos);
        } catch (IllegalNumberCombinationException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.INFO, "error", e);
            return generateLottos(money, manualLottoCount);
        }
    }

    private static WinningLotto generateWinningLotto() {
        try {
            Lotto winningLottoTicket = new Lotto(InputView.askWinningLottoNumbers());
            LottoNumber bonusNumber = new LottoNumber(InputView.askBonusNumber());
            return new WinningLotto(winningLottoTicket, bonusNumber);
        } catch (IllegalNumberCombinationException | IllegalLottoNumberException e) {
            LOGGER.log(Level.INFO, "error", e);
            return generateWinningLotto();
        }
    }

    private static ManualLottoCount generateManualLottoCount(Money money) {
        try {
            int input = InputView.askManualLottoCount();
            return new ManualLottoCount(input, money);
        } catch (IllegalCountException e) {
            LOGGER.log(Level.INFO, "error", e);
            return generateManualLottoCount(money);
        }
    }

    private static Money generateMoney() {
        try {
            String userInput = InputView.askMoney();
            return new Money(userInput);
        } catch (IllegalMoneyException e) {
            LOGGER.log(Level.INFO, "error", e);
            return generateMoney();
        }
    }

}
