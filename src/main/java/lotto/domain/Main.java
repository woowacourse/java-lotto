package lotto.domain;

import lotto.domain.model.*;
import lotto.domain.model.Number;
import lotto.domain.utils.AutoLottoGenerator;
import lotto.domain.utils.ManualLottoGenerator;
import lotto.domain.view.InputView;
import lotto.domain.view.OutputView;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PurchasedLottos purchasedLottos = new PurchasedLottos();

        Money money = new Money(InputView.inputMoney());                           // 구입 금액
        int manualLottoSize = InputView.inputManualLottoSize(money);               // 수동 로또 갯수

        purchasedLottos.addLottos(makeManualLotto(manualLottoSize));               // 수동 로또 생성
        purchasedLottos.addLottos(makeAutoLotto(money, manualLottoSize));          // 자동 로또 생성
        OutputView.printPurchasedLottoResult(purchasedLottos, manualLottoSize);    // 로또 구입 결과 출력

        WinningLotto winningLotto = makeWinningLotto();                            // 지난 주 당첨 로또 생성

        List<Rank> ranks = judgeRank(purchasedLottos, winningLotto);               // 당첨 확인, 등수 판단
        Map<Rank, Integer> prizeResult = calculatePrize(ranks);                    // 상금 계산
        OutputView.printLottoResult(money, prizeResult);                           // 결과 출력
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

    private static Map<Rank, Integer> calculatePrize(List<Rank> ranks) {
        Map<Rank, Integer> winResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            winResult.put(rank, 0);
        }

        for (Rank rank : ranks) {
            winResult.put(rank, (winResult.get(rank) + rank.getPrize()));
        }
        return winResult;
    }

    private static List<Rank> judgeRank(PurchasedLottos purchasedLottos, WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();

        for (Lotto lotto: purchasedLottos.getLottos()) {
            ranks.add(Rank.valueOf(winningLotto.matchCount(lotto), winningLotto.matchBonusNumber(lotto)));
        }
        return ranks;
    }

}

