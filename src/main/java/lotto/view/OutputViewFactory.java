package lotto.view;

import com.google.common.base.Joiner;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.PurchaseInformation;
import lotto.domain.Rank;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.Rank.SECOND;

public class OutputViewFactory {
    private static final String START_BRACE = "[";
    private static final String END_BRACE = "]";
    private static final String JOINER = ", ";
    private static final String PURCHASE_MESSAGE_FORMAT = "수동으로 %d장, 자동으로 %d장 구매했습니다.";
    private static final String YIELD_FORMAT = "총 수익률은 %.1f%% 입니다.";

    public static String outputLottosPurchaseMessage(PurchaseInformation purchaseInformation) {
        return String.format(PURCHASE_MESSAGE_FORMAT,
                purchaseInformation.getManualLottoCount(),
                purchaseInformation.getAutoLottoCount());
    }

    public static List<String> outputLottos(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(lotto -> makeNumbersView(lotto.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static String makeNumbersView(List<Integer> numbers) {
        StringBuilder builder = new StringBuilder(START_BRACE);
        builder.append(Joiner.on(JOINER).join(numbers));
        builder.append(END_BRACE);
        return builder.toString();
    }

    public static List<String> outputResult(LottoResult lottoResult) {
        return Rank.winningValues().stream()
                .map(rank -> makeRankMessage(rank, lottoResult.getRankCount(rank)))
                .collect(Collectors.toList());
    }

    private static String makeRankMessage(Rank rank, int count) {
        StringBuilder message = new StringBuilder();
        message.append(rank.getCountOfMatch());
        message.append("개 일치");
        if (rank == SECOND) {
            message.append(", 보너스 볼 일치");
        }
        message.append("(");
        message.append(rank.getWinningMoney());
        message.append("원)- ");
        message.append(count);
        message.append("개");
        return message.toString();
    }

    public static String outputYield(LottoResult lottoResult) {
        return String.format(YIELD_FORMAT, lottoResult.calculateYield());
    }
}
