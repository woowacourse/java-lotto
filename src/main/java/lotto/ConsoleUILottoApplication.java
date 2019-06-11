package lotto;

import lotto.domain.*;
import lotto.domain.generate.LottosFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleUILottoApplication {

    public static void main(String[] args) {
        Price price = new Price(InputView.inputPrice());
        LottosFactory lottosFactory = new LottosFactory(price, InputView.inputSelfCount());
        List<String> selfInputLottos = InputView.inputSelfLottos(lottosFactory.getCountOfSelf());
        Lottos lottos = lottosFactory.generateLottos(selfInputLottos, price);
        OutputView.printLottos(lottos.getLottos(), lottosFactory.getCountOfSelf());
        WinningLotto winningLotto = WinningLotto.generateWinningLotto(InputView.inputWinningNumber(), InputView.inputBonusBall());
        LottoResult lottoResult = LottoResult.generateLottoResult(lottos, winningLotto);
        OutputView.printStatistic(lottoResult);
        OutputView.printYield(lottoResult.findYield(price.getPrice()));
    }

}
