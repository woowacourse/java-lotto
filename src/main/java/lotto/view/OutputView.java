package lotto.view;


import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;

public class OutputView {

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printPurchaseAmountGuideMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.getNumOfLottos() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            List<Integer> lottoValues = lotto.getNumbers()
                .stream()
                .map(number -> number.getValue())
                .collect(Collectors.toList());
            System.out.println(lottoValues.toString());
        }
    }
}
