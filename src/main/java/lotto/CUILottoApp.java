package lotto;

import java.util.List;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class CUILottoApp {
    public static void main(String[] args) {
        Money money = InputView.createMoney();
        List<Lotto> lottos = LottoFactory.createLottos(money.getLottoSize(), new AutoLottoCreator());
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = InputView.createWinningLotto();
        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        for (WinnerType value : WinnerType.values()) {
            System.out.println(lottoResult.getWinnerTypeValue(value));
        }

    }
}
