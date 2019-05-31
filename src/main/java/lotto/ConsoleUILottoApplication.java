package lotto;

import lotto.domain.*;
import lotto.domain.Number;
import lotto.utils.Converter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUILottoApplication {
    private static final int MIN_SELF_LOTTO_COUNT = 0;
    public static void main(String[] args) {
        Price price = new Price(InputView.inputPrice());
        int selfCount = getSelfCount(price,InputView.inputSelfCount(price));
        Lottos lottos = getLottos(price, selfCount);
        OutputView.printLottos(lottos.getLottos(), selfCount);
        WinningLotto winningLotto = getWinningLotto();
        LottoResult lottoResult = getLottoResult(lottos, winningLotto);
        OutputView.printStatistic(lottoResult.getResults());
        OutputView.printYield(lottoResult.findYield(price.getPrice()));
    }

    static int getSelfCount(Price price, int inputSelfCount) {
        if (inputSelfCount < MIN_SELF_LOTTO_COUNT || inputSelfCount > price.getCountOfLotto()) {
            throw new IllegalArgumentException("올바르지 않은 횟수입니다.");
        }
        return inputSelfCount;
    }

    private static LottoResult getLottoResult(Lottos lottos, WinningLotto winningLotto) {
        List<Rank> ranks = lottos.match(winningLotto);
        return new LottoResult(ranks);
    }

    private static WinningLotto getWinningLotto() {
        Lotto lotto = new Lotto(Converter.convertNumbers(InputView.inputWinningNumber()));
        return new WinningLotto(lotto, Number.of(InputView.inputBonusBall()));
    }

    private static Lottos getLottos(Price price, int selfSize) {
        List<Lotto> lottos = AutoLottoFactory.generateAutoLottos(price.getCountOfLotto() - selfSize);
        lottos.addAll(getSelfLotto(selfSize));
        return new Lottos(lottos);
    }

    private static List<Lotto> getSelfLotto(int selfSize) {
        List<Lotto> selfLottos = new ArrayList<>();
        List<String> self = InputView.inputSelfNumbers(selfSize);
        for (String input : self) {
            selfLottos.add(new Lotto(Converter.convertNumbers(input)));
        }
        return selfLottos;
    }

}
