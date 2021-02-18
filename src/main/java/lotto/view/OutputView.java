package lotto.view;

import lotto.domain.lotto.Lottos;

public class OutputView {

    public static void numPurchasedLotto(Integer numLotto) {
        System.out.println(numLotto + "개를 구매했습니다.");
    }

    ;

    public static void inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void lottosPrint(Lottos purchasedLottos) {
        System.out.println(purchasedLottos);
    }
}
