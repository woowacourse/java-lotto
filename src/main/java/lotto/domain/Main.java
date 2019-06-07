package lotto.domain;

import lotto.domain.model.Number;
import lotto.domain.model.*;
import lotto.domain.utils.ShuffledNumberGenerator;
import lotto.domain.view.InputView;
import lotto.domain.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PurchasedLotto purchasedLotto = new PurchasedLotto();
        List<Lotto> manualLotto = new ArrayList<>();
        List<Lotto> autoLotto = new ArrayList<>();
        Money money = new Money(InputView.inputMoney());
        int manualLottoSize = InputView.inputManualLottoSize(money);

        InputView.printInputManualLottoMessage(manualLottoSize);
        // 수동, 자동 로또 생성

        while (manualLotto.size() < manualLottoSize) {
            System.out.println("로또 번호를 입력하세요." + manualLotto.size() + "/" + manualLottoSize);
            manualLotto.add(new Lotto(InputView.inputLottoNumber()));
        }

        while (autoLotto.size() < (money.availablePurchaseCount() - manualLottoSize)) {
            autoLotto.add(new Lotto(ShuffledNumberGenerator.getShuffledNumbers()));
        }

        purchasedLotto.addLotto(manualLotto);
        purchasedLotto.addLotto(autoLotto);
        OutputView.printPurchasedLottoResult(purchasedLotto, manualLottoSize);

        InputView.printInputWinningLottoMessage();
        Lotto winLotto = new Lotto(InputView.inputLottoNumber());
        Number bonusNumber = InputView.inputBonusNumber(winLotto);
        WinningLotto winningLotto = new WinningLotto(winLotto, bonusNumber);

        List<Rank> ranks = winningLotto.match(purchasedLotto);
        Map<Rank, Integer> prizeResult = winningLotto.calculatePrize(ranks);
        OutputView.printLottoResult(money, prizeResult);
    }
}
