package lotto.view;

import com.google.common.base.Joiner;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

import java.util.List;

import static lotto.domain.Rank.SECOND;

public class OutputView {
    private static final String START_BRACE = "[";
    private static final String END_BRACE = "]";
    private static final String JOINER = ", ";
    private static final String NEW_LINE = "\n";
    private static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------\n";
    private static final String YIELD_FORMAT = "총 수익률은 %.1f%% 입니다.";

    public static void requestManualLottosMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void requestWinningNumbersMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void outputLottosPurchaseMessage(int manualLottosSize, int autoLottosSize) {
        System.out.println("수동으로 " + manualLottosSize + "장, 자동으로" + autoLottosSize + "장 구매했습니다.");
    }

    public static void outputLottos(Lottos lottos) {
        StringBuilder builder = new StringBuilder();
        for (Lotto lotto : lottos.getLottos()) {
            builder.append(makeNumbersView(lotto.getLottoNumbers()));
        }
        System.out.println(builder);
    }

    private static StringBuilder makeNumbersView(List<Integer> numbers) {
        StringBuilder builder = new StringBuilder(START_BRACE);
        builder.append(Joiner.on(JOINER).join(numbers));
        builder.append(END_BRACE);
        builder.append(NEW_LINE);
        return builder;
    }

    public static void outputResult(LottoResult lottoResult) {
        StringBuilder builder = new StringBuilder();
        builder.append(STATISTICS_MESSAGE);
        for (Rank rank : Rank.winningValues()) {
            builder.append(makeRankMessage(rank));
            builder.append(lottoResult.getRankCount(rank));
            builder.append("개\n");
        }
        builder.append(String.format(YIELD_FORMAT, lottoResult.calculateYield()));
        System.out.println(builder);
    }

    private static StringBuilder makeRankMessage(Rank rank) {
        StringBuilder message = new StringBuilder();
        message.append(rank.getCountOfMatch());
        message.append("개 일치");
        if (rank == SECOND) {
            message.append(", 보너스 볼 일치");
        }
        message.append("(");
        message.append(rank.getWinningMoney());
        message.append("원)- ");
        return message;
    }
}
