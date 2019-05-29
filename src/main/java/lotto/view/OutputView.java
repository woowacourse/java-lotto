package lotto.view;

import lotto.domain.LottoBuyer;

import java.util.List;

public class OutputView {
    private static final String NEW_LINE = System.getProperty("line.separator");

    public static void printErrorMsg(Exception e) {
        System.out.println(e.getMessage() + NEW_LINE);
    }

    public static void printContainingLottos(LottoBuyer person) {
        List<String> lottos = person.showLottos();
        System.out.println(NEW_LINE + lottos.size() + "개를 구매하셨습니다.");
        for (String lotto : lottos) {
            System.out.println(lotto);
        }
        System.out.print(NEW_LINE);
    }
}
