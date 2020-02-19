package view;

import domain.Lotto;
import domain.LottoNumber;

import java.util.List;

public class OutputView {
    public static final String NEW_LINE = System.lineSeparator();
    public static final java.lang.String COMMA = ", ";
    public static final int LAST_COMMA_REMOVER = 2;

    public static void printInputPurchaseAmountMessage() {
        System.out.println("구매금액을 입력해 주세요");
    }

    public static void printPurchaseCountMessage(int calculateCount) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, calculateCount);
    }

    public static void printLottoDummy(List<Lotto> lottoDummy) {
        for (Lotto eachLotto : lottoDummy){
            printEachLotto(eachLotto);
        }
    }

    private static void printEachLotto(Lotto eachLotto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        List<LottoNumber> lotto = eachLotto.getLotto();
        for (LottoNumber number : lotto){
            stringBuilder.append(number)
                            .append(COMMA);
        }
        stringBuilder.setLength(stringBuilder.length()- LAST_COMMA_REMOVER);
        stringBuilder.append("]");
        System.out.println(stringBuilder);
    }

    public static void printInputWinningNumbersMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printInputBonusNumberMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printResult() {
        System.out.println("당첨 통계" + NEW_LINE + "------------");

    }
}
