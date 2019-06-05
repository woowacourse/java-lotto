package lotto.domain;

import lotto.domain.model.Number;
import lotto.domain.model.*;
import lotto.domain.utils.AutoLottoGenerator;
import lotto.domain.utils.ManualLottoGenerator;
import lotto.domain.utils.ShuffledNumberGenerator;
import lotto.domain.view.InputView;
import lotto.domain.view.OutputView;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PurchasedLottos purchasedLottos = new PurchasedLottos();

        Money money = new Money(InputView.inputMoney());                           // 구입 금액
        int manualLottoSize = InputView.inputManualLottoSize(money);               // 수동 로또 갯수

        InputView.printInputManualLottoMessage();
        purchasedLottos.addLottos(new ManualLottoGenerator(manualLottoSize).generate(InputView.inputLottoNumber()));                               // 수동 로또 생성
        purchasedLottos.addLottos(new AutoLottoGenerator(money, manualLottoSize).generate(ShuffledNumberGenerator.getShuffledNumbers()));          // 자동 로또 생성
        OutputView.printPurchasedLottoResult(purchasedLottos, manualLottoSize);  // 로또 구입 결과 출력

        InputView.printInputWinningLottoMessage();
        Lotto winLotto = new Lotto(InputView.inputLottoNumber());
        Number bonusNumber = InputView.inputBonusNumber(winLotto);
        WinningLotto winningLotto = new WinningLotto(winLotto, bonusNumber);  // 지난 주 당첨 로또 생성
        List<Rank> ranks = winningLotto.match(purchasedLottos);  // 당첨 확인, 등수 판단
        Map<Rank, Integer> prizeResult = winningLotto.calculatePrize(ranks);  // 상금 계산
        OutputView.printLottoResult(money, prizeResult);  // 결과 출력
    }
}

