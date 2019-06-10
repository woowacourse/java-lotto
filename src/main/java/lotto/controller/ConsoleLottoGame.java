package lotto.controller;

import lotto.model.*;
import lotto.view.Input;
import lotto.view.Output;

import java.util.ArrayList;
import java.util.List;

class ConsoleLottoGame {
    private static final LottoRule RULE;
    private static final LottoMaker MAKER;
    private static final String MESSAGE_PURCHASE_AMOUNT;
    private static final String MESSAGE_MANUAL_COUNT;
    private static final String MESSAGE_MANUAL_NUMBERS;
    private static final String MESSAGE_WIN_NUMBERS;
    private static final String MESSAGE_BONUS_BALL;
    private static final String MESSAGE_LOW_MONEY;

    static {
        MESSAGE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
        MESSAGE_MANUAL_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
        MESSAGE_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
        MESSAGE_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
        MESSAGE_BONUS_BALL = "보너스 볼을 입력해 주세요.";
        MESSAGE_LOW_MONEY = "금액이 모자랍니다.";
        RULE = new KoreaLottoRule();
        MAKER = new RandomLottoMaker(RULE);
    }

    private static int getAllPurchaseCount() {
        final int result = Input.getSingleInt(MESSAGE_PURCHASE_AMOUNT) / RULE.getPrice();
        if (result < 1) {
            throw new IllegalArgumentException(MESSAGE_LOW_MONEY);
        }
        return result;
    }

    private static int getAutoPurchaseCount(final int AllPurchaseCount, final int manualPurchaseCount) {
        final int result = AllPurchaseCount - manualPurchaseCount;
        if (result < 0) {
            throw new IllegalArgumentException(MESSAGE_LOW_MONEY);
        }
        return result;
    }

    private static List<Lotto> manualLottos(final int purchaseAmount) {
        final List<Lotto> lottos = new ArrayList<>();
        if (purchaseAmount >= 1) {
            System.out.println(MESSAGE_MANUAL_NUMBERS);
        }
        while (lottos.size() < purchaseAmount) {
            try {
                final Lotto temp = new Lotto(Input.tryGetNumbers(), RULE);
                lottos.add(temp);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return lottos;
    }

    private static List<Lotto> autoLottos(final int purchaseAmount) {
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            lottos.add(MAKER.getLotto());
        }
        return lottos;
    }

    private static WinningLotto getWinningLotto() {
        System.out.println(MESSAGE_WIN_NUMBERS);
        Lotto lotto = null;
        int bonusNo = -1;
        while (lotto == null) {
            try {
                lotto = new Lotto(Input.tryGetNumbers(), RULE);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        while (!RULE.isValidNumberRange(bonusNo)) {
            bonusNo = Input.getSingleInt(MESSAGE_BONUS_BALL);
        }
        return new WinningLotto(lotto, bonusNo);
    }

    public static void main(String[] args) {
        try {
            int AllPurchaseCount = getAllPurchaseCount();
            int manualPurchaseCount = Input.getSingleInt(MESSAGE_MANUAL_COUNT);
            int autoPurchaseCount = getAutoPurchaseCount(AllPurchaseCount, manualPurchaseCount);
            List<Lotto> lottos = manualLottos(manualPurchaseCount);
            lottos.addAll(autoLottos(autoPurchaseCount));
            Output.buyCount(manualPurchaseCount, autoPurchaseCount);
            Output.lottoList(lottos);
            WinningLotto winLotto = getWinningLotto();
            WinStat stat = new WinStat(lottos, winLotto, RULE);
            System.out.println(stat.makeString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
