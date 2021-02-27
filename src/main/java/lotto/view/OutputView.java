package lotto.view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Payment;
import lotto.domain.Reward;
import lotto.domain.Rewards;
import lotto.domain.dto.LottoTicketsDTO;

public class OutputView {

    private static final String BUY_LOTTO_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String DELIMITER = ", ";
    private static final String DELIMITER_HEAD = "[";
    private static final String DELIMITER_TAIL = "]";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String CONTOUR = "---------";
    private static final String MATCHES_MESSAGE = "%d개 일치 (%d원)- %d";
    private static final String FIVE_MATCHES_WITH_BONUS_NUMBER = "%d개 일치, 보너스 볼 일치 (%d원)- %d";
    private static final String TOTAL_PROFIT = "총 수익률은 %.2f 입니다.";
    private static final String FORMAT_STRING = "%s%s%s";
    private static final String LOTTO_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    public static void printBuyLottoCountMessage(final int selfCount, final int autoCount) {
        System.out.printf((BUY_LOTTO_COUNT_MESSAGE) + "%n", selfCount, autoCount - selfCount);
    }

    public static void printLottoMessage(List<Integer> values) {
        String numbers = values.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(DELIMITER));

        System.out.printf((FORMAT_STRING) + "%n", DELIMITER_HEAD, numbers, DELIMITER_TAIL);
    }

    public static void printResultMessage(Rewards rewards, Payment payment) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(CONTOUR);
        Arrays.stream(Reward.values())
            .sorted(Comparator.comparing(Reward::getWinningMoney))
            .filter(reward -> !reward.equals(Reward.NONE))
            .forEach(reward -> callMatchMessage(reward, rewards.getRankCount(reward)));
        System.out.printf((TOTAL_PROFIT) + "%n", rewards.profit(payment.getPayment()));
    }

    private static void callMatchMessage(Reward reward, int count) {
        if (reward != Reward.SECOND) {
            System.out
                .printf((MATCHES_MESSAGE) + "%n", reward.getHitCount(), reward.getWinningMoney(),
                    count);
            return;
        }
        System.out.printf((FIVE_MATCHES_WITH_BONUS_NUMBER) + "%n", reward.getHitCount(),
            reward.getWinningMoney(), count);
    }

    public static void printWinningLottoMessage() {
        System.out.println(LOTTO_NUMBERS_MESSAGE);
    }

    public static void printLottoTicketsMessage(LottoTicketsDTO lottoTicketsDTO) {
        for (Lotto lotto : lottoTicketsDTO.getLottoTickets()) {
            printLottoMessage(lotto.getLottoNumbers());
        }
        System.out.println();
    }
}