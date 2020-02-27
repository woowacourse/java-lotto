package Lotto.views;

import Lotto.domain.Lottos;

import java.util.stream.Collectors;

public class OutputView {
    private static final String EMPTY_STRING = "";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %.3f %%입니다.";
    private static final String SECOND_RANK_ADDITIONAL_MESSAGE = "보너스 볼 일치 ";
    private static final String PURCHASED_LOTTO_MESSAGE = "개를 구매했습니다.";
    private static final String NEW_LINE = "\n";
    private static final String STATISTICS_FORMAT = "%d개 일치 %s%d원 - %d개%s";


    public static void showPurchasedLottoCount(int purchasedLottoCount) {
        System.out.println(purchasedLottoCount + PURCHASED_LOTTO_MESSAGE);
    }

    public static void showPurchasedAutoLottos(Lottos purchasedAutoLottos) {
        System.out.println(purchasedAutoLottos.getLottos());
    }
}
