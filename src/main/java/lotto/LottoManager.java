package lotto;

import lotto.domain.*;
import lotto.exception.LottoException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoManager {

    public static LottoGroup createLotto() {
        LottoGroup lottoGroup = createLottoGroup(InputView.getMoney());
        OutputView.printBoughtLotto(lottoGroup);
        return lottoGroup;
    }

    public static WinningLotto getWinningLotto() {
        try {
            return new WinningLotto(InputView.getWinningNumbers(), InputView.getBonusNumber());
        } catch (LottoException e) {
            OutputView.printMessage(e.getMessage());
            return getWinningLotto();
        }
    }

    public static void printResult(LottoGroup lottoGroup, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        lottoGroup.lottoGroup()
                .stream()
                .map(winningLotto::matchRank)
                .forEach(lottoResult::add);
        OutputView.printLottoResult(lottoResult);
    }

    private static LottoGroup createLottoGroup(Money money) {
        try {
            LottoSeller lottoSeller = new LottoSeller();
            return lottoSeller.sellLotto(money);
        } catch (LottoException e) {
            OutputView.printMessage(e.getMessage());
            return createLottoGroup(money);
        }
    }
}
