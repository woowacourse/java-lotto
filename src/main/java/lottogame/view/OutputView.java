package lottogame.view;

import lottogame.domain.PurchaseLotto;

public class OutputView {
    public static void printPurchaseResult(PurchaseLotto purchaseLotto) {
        System.out.println(purchaseLotto.numberOfLottos()+"개를 구매했습니다.");
        System.out.println(purchaseLotto);
    }
}
