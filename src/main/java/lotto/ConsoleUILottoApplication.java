package lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.view.ConsoleInputView;
import com.woowacourse.lotto.view.ConsoleOutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        List<Lotto> lottos = getLottos();

        ConsoleOutputView.printLottos(lottos);
        WinningLotto winningLotto = ConsoleInputView.promptWinningNumber();
        WinningAggregator aggregator = new WinningAggregator();
        lottos.forEach(l -> aggregator.addResult(winningLotto.match(l)));
        ConsoleOutputView.printAggregation(aggregator);
    }

    private static List<Lotto> getLottos() {
        int quantity = ConsoleInputView.promptBuyingMoney().getQuantity();
        NumberGenerator numberGenerator = new RandomNumberGenerator(Lotto.LOTTO_MIN, Lotto.LOTTO_MAX);
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < quantity; i++) {
            lottos.add(LottoFactory.createLotto(numberGenerator));
        }
        return lottos;
    }
}
