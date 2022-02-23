package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class Output {
    private static final String ASK_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT = "%d개를 구매했습니다.\n";

    public static void askPurchaseAmount() {
        System.out.println(ASK_PURCHASE_AMOUNT);
    }

    public static void lottoCount(int lottoCount) {
        System.out.printf(LOTTO_COUNT, lottoCount);
    }

    public static void lottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            String numbers = String.join(", ", lotto.getLottoNumbers());
            System.out.println("[" + numbers + "]");
        }
    }
}
