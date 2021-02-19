package lotto;

import lotto.domain.*;
import lotto.util.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoControllerMain {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        Lottos lottos = new Lottos(buyLotto(money.countBuyLotto()));

        OutputView.showBuyLotto(lottos.getLottos());

        List<Result> results = lottos.getResults(
                new WinningNumber(InputView.winningNumbers()),
                new BonusNumber(InputView.bonusNumber())
        );
        OutputView.result(results, money.calculateProfitRate(Result.calculateProfit(results)));
    }

    public static List<Lotto> buyLotto(int count){
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoGenerator.make()));
        }
        return lottos;
    }
}
