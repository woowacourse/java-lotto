package lotto.view;

import lotto.domain.Lottos;

public class OutputView {

    private static final String TOTAL_LOTTO_COUNT_MESSAGE = "개를 구매했습니다.";

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printTotalLottoCount(int totalLottoCount) {
        System.out.println(totalLottoCount + TOTAL_LOTTO_COUNT_MESSAGE);
    }

    public static void printLottos(Lottos lottos) {
        lottos.getLottos().forEach(System.out::println);
    }
}
