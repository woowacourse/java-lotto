package view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoReward;
import domain.Lottos;

public class OutputView {

    private static final String RESPONSE_MESSAGE_PURCHASED_LOTTO = "개를 구매했습니다.";
    private static final String REWARD_SECOND_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
    private static final String REWARD_DEFAULT_FORMAT = "%d개 일치 (%d원)- %d개\n";
    private static final String PROFIT_RATE_BENEFIT_FORMAT = "총 수익률은 (%.2f)입니다.";
    private static final String PROFIT_RATE_LOSS_FORMAT = "총 수익률은 (%.2f)입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String WINNING_STATISTICS = "\n당첨 통계";
    private static final String PRINT_DECISION = "---------";
    private static final int SECOND_DECIMAL_POINT = 100;
    private static final int BENEFIT_STANDARD = 1;

    private OutputView() {
    }

    public static void showPurchasedLottos(Lottos lottos) {
        System.out.println(lottos.getLottos().size() + RESPONSE_MESSAGE_PURCHASED_LOTTO);

        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLottoNumbers().stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList()));
        }
    }

    public static void showWinningStatistics(Map<LottoReward, Integer> winningStatistics) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(PRINT_DECISION);

        List<LottoReward> rewards = Arrays.asList(LottoReward.values());
        Collections.reverse(rewards);

        for (LottoReward reward : rewards) {
            printLottoReward(reward, winningStatistics.get(reward));
        }
    }

    private static void printLottoReward(LottoReward reward, int rewardCount) {
        if (reward.equals(LottoReward.NONE)) {
            return;
        }
        if (reward.equals(LottoReward.SECOND)) {
            System.out.printf(REWARD_SECOND_FORMAT, reward.getMatchCount(), reward.getPrice(), rewardCount);
            return;
        }

        System.out.printf(REWARD_DEFAULT_FORMAT, reward.getMatchCount(), reward.getPrice(), rewardCount);
    }

    public static void showProfitRate(double profitRate) {
        double test = Math.floor(profitRate * SECOND_DECIMAL_POINT) / SECOND_DECIMAL_POINT;

        if (profitRate < BENEFIT_STANDARD) {
            System.out.printf(PROFIT_RATE_LOSS_FORMAT, test);
        }
        if (profitRate >= BENEFIT_STANDARD) {
            System.out.printf(PROFIT_RATE_BENEFIT_FORMAT, test);
        }
    }
}
