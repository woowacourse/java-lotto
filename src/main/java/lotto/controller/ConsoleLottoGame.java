package lotto.controller;

import lotto.model.*;
import lotto.view.ConsoleInput;
import lotto.view.ConsoleOutput;

import java.util.List;

class ConsoleLottoGame {
    private static final LottoRule RULE;
    private static final LottoMaker MAKER;
    private static final String ERROR_OVER_COUNT;

    static {
        ERROR_OVER_COUNT = "구입한 로또보다 많은 개수입니다.";
        RULE = new KoreaLottoRule();
        MAKER = new RandomLottoMaker(RULE);
    }

    private static int getAutoPurchaseCount(final int AllPurchaseCount, final int manualPurchaseCount) {
        final int result = AllPurchaseCount - manualPurchaseCount;
        if (result < 0) {
            throw new IllegalArgumentException(ERROR_OVER_COUNT);
        }
        return result;
    }

    public static void main(final String[] args) {
        ConsoleInput input = new ConsoleInput(RULE);
        try {
            int AllPurchaseCount = input.allPurchaseCount();
            int manualPurchaseCount = ConsoleInput.manualPurchaseCount();
            int autoPurchaseCount = getAutoPurchaseCount(AllPurchaseCount, manualPurchaseCount);
            List<Lotto> lottos = input.manualLottos(manualPurchaseCount);
            lottos.addAll(MAKER.getAutoLottos(autoPurchaseCount));
            ConsoleOutput.buyCount(manualPurchaseCount, autoPurchaseCount);
            ConsoleOutput.lottoList(lottos);
            WinningLotto winLotto = input.winningLotto();
            WinStat stat = new WinStat(lottos, winLotto, RULE);
            ConsoleOutput.statString(stat);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
