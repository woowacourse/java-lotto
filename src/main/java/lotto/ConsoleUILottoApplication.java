package lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.view.ConsoleInputView;
import com.woowacourse.lotto.view.ConsoleOutputView;

import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        List<Lotto> lottos = getLottos();
        WinningLotto winningLotto = ConsoleInputView.promptWinningNumber();
        WinningAggregator aggregator = new WinningAggregator();
        lottos.forEach(l -> aggregator.addResult(winningLotto.match(l)));
        ConsoleOutputView.printAggregation(aggregator);
    }

    private static List<Lotto> getLottos() {
        LottoQuantity quantity = ConsoleInputView.promptBuyingMoney().getQuantity();
        NumberGenerator numberGenerator = new RandomNumberGenerator(LottoNumber.LOTTO_NUMBER_MIN, LottoNumber.LOTTO_NUMBER_MAX);
        List<Lotto> lottos = getManualLottos(quantity);
        final int numOfManuals = lottos.size();
        for (int i = 0; i < quantity.toInt() - numOfManuals; i++) {
            lottos.add(LottoFactory.createLotto(numberGenerator));
        }
        ConsoleOutputView.printLottos(lottos, numOfManuals);
        return lottos;
    }

    private static List<Lotto> getManualLottos(LottoQuantity totalLottoQuantity) {
        LottoQuantity manualLottoQuantity = ConsoleInputView.promptManualLottoQuantity();

        while (manualLottoQuantity.compareTo(totalLottoQuantity) == 1) {
            ConsoleOutputView.printError(String.format("최대 %d장 까지 구매할 수 있습니다.", totalLottoQuantity.toInt()));
            manualLottoQuantity = ConsoleInputView.promptManualLottoQuantity();
        }

        return ConsoleInputView.promptManualLottoNumbers(manualLottoQuantity);
    }
}
