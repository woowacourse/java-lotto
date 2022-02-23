package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class Output {
    private static final String ASK_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT = "%d개를 구매했습니다.\n";
    private static final String LAST_WEEKS_WINNING_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL = "보너스 볼을 입력해 주세요.";

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

    public static void askLastWeeksWinningNumber() {
        System.out.println(LAST_WEEKS_WINNING_NUMBERS);
    }

    public static void askBonusBall() {
        System.out.println(BONUS_BALL);
    }
}
