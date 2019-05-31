package lotto;

import lotto.domain.*;
import lotto.domain.generate.LottosFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
    private static final int MIN_SELF_LOTTO_COUNT = 0;

    public static void main(String[] args) {
        Price price = new Price(InputView.inputPrice());
        int selfCount = getSelfCount(price, InputView.inputSelfCount());
        Lottos lottos = LottosFactory.generateLottos(price, InputView.inputSelfNumbers(selfCount));
        OutputView.printLottos(lottos.getLottos(), selfCount);
        WinningLotto winningLotto = WinningLotto.generateWinningLotto(InputView.inputWinningNumber(), InputView.inputBonusBall());
        LottoResult lottoResult = LottoResult.generateLottoResult(lottos, winningLotto);
        OutputView.printStatistic(lottoResult.getResults());
        OutputView.printYield(lottoResult.findYield(price.getPrice()));
    }

    static int getSelfCount(Price price, int inputSelfCount) {
        if (inputSelfCount < MIN_SELF_LOTTO_COUNT || inputSelfCount > price.getCountOfLotto()) {
            throw new IllegalArgumentException("올바르지 않은 횟수입니다.");
        }
        return inputSelfCount;
    }

}
