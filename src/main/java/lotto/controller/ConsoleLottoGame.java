package lotto.controller;

import lotto.model.*;
import lotto.view.ConsoleInput;
import lotto.view.ConsoleOutput;

import java.util.ArrayList;
import java.util.List;

class ConsoleLottoGame {
    private static final LottoRule RULE;
    private static final LottoMaker MAKER;
    private static final String MESSAGE_MANUAL_COUNT;
    private static final String MESSAGE_MANUAL_NUMBERS;
    private static final String MESSAGE_WIN_NUMBERS;
    private static final String MESSAGE_BONUS_BALL;

    static {
        RULE = new KoreaLottoRule();
        MAKER = new RandomLottoMaker(RULE);
        MESSAGE_MANUAL_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
        MESSAGE_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
        MESSAGE_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
        MESSAGE_BONUS_BALL = "보너스 볼을 입력해 주세요.";
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
            final int allPurchaseCount = input.allPurchaseCount();
            final int manualPurchaseCount = ConsoleInput.singleInt(MESSAGE_MANUAL_COUNT);
            final int autoPurchaseCount = allPurchaseCount - manualPurchaseCount;
            final int allPurchaseAmount = allPurchaseCount * RULE.getPrice();
            final Lottos lottos = new Lottos(allPurchaseCount, manualPurchaseCount);
            lottos.add(inputManyLottos(manualPurchaseCount));
            lottos.add(MAKER.getAutoLottos(autoPurchaseCount));
            ConsoleOutput.buyCount(manualPurchaseCount, autoPurchaseCount);
            ConsoleOutput.lottoList(lottos);
            final WinningLotto winLotto = inputWinningLotto();
            final LottoGame game = new LottoGame.Builder(RULE, MAKER)
                    .purchaseAmount(allPurchaseAmount)
                    .purchasedLottos(lottos)
                    .winLotto(winLotto)
                    .build();
            ConsoleOutput.statString(game.getStat());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
