package lotto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.model.prize.PrizeInformations;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {

    public static void run() {
        Money money = askMoneyAmount();

        Lottos lottos = Lottos.of(money, InputView.askManualCount());
        purchaseLottos(lottos);
        ResultView.showPurchaseCount(lottos.getSize());
        ResultView.showLottos(LottoDTO.from(lottos));

        PrizeInformations prizeInformations = PrizeInformations.from(lottos.match(makeWinningLotto()));
        ResultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformations));
        ResultView.showEarningRate(prizeInformations.calculateEarningRate(money));
    }

    private static Money askMoneyAmount() {
        return Money.from(InputView.askMoneyAmount());
    }

    private static void purchaseLottos(Lottos lottos) {
        InputView.askManualNumbers();
        purchaseManualLotto(lottos);
        lottos.purchaseAuto();
    }

    private static void purchaseManualLotto(Lottos lottos) {
        while (lottos.isManualAvailable()) {
            lottos.purchaseManual(List.of(InputView.readNumbers()));
        }
    }

    private static WinningLotto makeWinningLotto() {
        LottoNumbers winningNumbers = askWinningNumbers();
        return new WinningLotto(winningNumbers, askBonusNumber());
    }

    private static LottoNumbers askWinningNumbers() {
        String[] winningNumbersInput = InputView.askWinningNumbers();

        return LottoNumbers.from(List.of(winningNumbersInput));
    }

    private static LottoNumber askBonusNumber() {
        String bonusNumberInput = InputView.askBonusNumber();

        return LottoNumber.from(bonusNumberInput);
    }

}
