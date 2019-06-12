package lotto.controller;

import lotto.model.*;
import lotto.view.ConsoleInput;
import lotto.view.ConsoleOutput;

import java.util.ArrayList;
import java.util.List;

class ConsoleLottoGame {
    private static final LottoRule RULE;
    private static final LottoMaker MAKER;
    private static final String ERROR_OVER_COUNT;
    private static final String MESSAGE_MANUAL_COUNT;
    private static final String MESSAGE_MANUAL_NUMBERS;
    private static final String MESSAGE_WIN_NUMBERS;
    private static final String MESSAGE_BONUS_BALL;

    static {
        RULE = new KoreaLottoRule();
        MAKER = new RandomLottoMaker(RULE);
        ERROR_OVER_COUNT = "구입한 로또보다 많은 개수입니다.";
        MESSAGE_MANUAL_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
        MESSAGE_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
        MESSAGE_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
        MESSAGE_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    }

    private static int getAutoPurchaseCount(final int AllPurchaseCount, final int manualPurchaseCount) {
        final int result = AllPurchaseCount - manualPurchaseCount;
        if (result < 0) {
            throw new IllegalArgumentException(ERROR_OVER_COUNT);
        }
        return result;
    }

    private static Lotto inputOneLotto() {
        Lotto lotto = null;
        while (lotto == null) {
            try {
                lotto = new Lotto(ConsoleInput.tryGetNumbers(), RULE);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return inputOneLotto();
            }
        }
        return lotto;
    }

    private static List<Lotto> inputManyLottos(final int purchaseAmount) {
        final List<Lotto> lottos = new ArrayList<>();
        if (purchaseAmount >= 1) {
            System.out.println(MESSAGE_MANUAL_NUMBERS);
        }
        while (lottos.size() < purchaseAmount) {
            lottos.add(inputOneLotto());
        }
        return lottos;
    }

    private static WinningLotto inputWinningLotto() {
        System.out.println(MESSAGE_WIN_NUMBERS);
        Lotto lotto = inputOneLotto();
        int bonusNo = -1;
        while (!RULE.isValidNumberRange(bonusNo)) {
            bonusNo = ConsoleInput.singleInt(MESSAGE_BONUS_BALL);
        }
        return new WinningLotto(lotto, bonusNo);
    }

    public static void main(final String[] args) {
        ConsoleInput input = new ConsoleInput(RULE);
        try {
            int AllPurchaseCount = input.allPurchaseCount();
            int manualPurchaseCount = ConsoleInput.singleInt(MESSAGE_MANUAL_COUNT);
            int autoPurchaseCount = getAutoPurchaseCount(AllPurchaseCount, manualPurchaseCount);
            List<Lotto> lottos = inputManyLottos(manualPurchaseCount);
            lottos.addAll(MAKER.getAutoLottos(autoPurchaseCount));
            ConsoleOutput.buyCount(manualPurchaseCount, autoPurchaseCount);
            ConsoleOutput.lottoList(lottos);
            WinningLotto winLotto = inputWinningLotto();
            WinStat stat = new WinStat(lottos, winLotto, RULE);
            ConsoleOutput.statString(stat);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
