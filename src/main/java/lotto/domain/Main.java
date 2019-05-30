package lotto.domain;

import lotto.domain.model.Lotto;
import lotto.domain.model.Money;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.WinningLotto;
import lotto.domain.utils.AutoLottoGenerator;
import lotto.domain.utils.ManualLottoGenerator;
import lotto.domain.view.InputView;
import lotto.domain.model.Number;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PurchasedLottos purchasedLottos = new PurchasedLottos();

        Money money = new Money(InputView.inputMoney());
        int manualLottoSize = InputView.inputManualLottoSize(money);

        purchasedLottos.addLottos(makeManualLotto(manualLottoSize));
        purchasedLottos.addLottos(makeAutoLotto(money, manualLottoSize));
        OutputView.printPurchasedLottoResult(purchasedLottos, manualLottoSize);

        WinningLotto winningLotto = makeWinningLotto();


    }

    private static List<Lotto> makeAutoLotto(Money money, int manualLottoSize) {
        List<Lotto> autoLottos = new ArrayList<>();
        int autoLottoSize = money.availablePurchseTicketCount() - manualLottoSize;

        for (int i = 1; i <= autoLottoSize; i++) {
            autoLottos.add(AutoLottoGenerator.makeLotto());
        }
        return autoLottos;
    }

    private static List<Lotto> makeManualLotto(int manualLottoSize) {
        InputView.printInputManualLottoMessage();

        List<Lotto> manualLottos = new ArrayList<>();
        Lotto lotto;

        for (int i = 1; i <= manualLottoSize; i++) {
            lotto = ManualLottoGenerator.makeLotto(InputView.inputLottoNumber());
            manualLottos.add(lotto);
        }
        return manualLottos;
    }

    private static WinningLotto makeWinningLotto() {
        InputView.printInputWinningLottoMessage();
        Lotto winLotto = ManualLottoGenerator.makeLotto(InputView.inputLottoNumber());
        Number bonusNumber = InputView.inputBonusNumber(winLotto);
        return new WinningLotto(winLotto, bonusNumber);
    }
}

