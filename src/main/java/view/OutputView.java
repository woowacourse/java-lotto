package view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoCount;
import domain.LottoNumber;
import domain.LottoReward;

public class OutputView {

    private static final int SECOND_DECIMAL_POINT = 100;
    private static final int BENEFIT_STANDARD = 1;

    private OutputView() {
    }

    public static void showPurchasedLottos(LottoCount lottoCount, List<Lotto> lottos) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", lottoCount.manualLottoCount(),
            lottoCount.autoLottoCount());

        for (Lotto lotto : lottos) {
            System.out.println(lotto.lottoNumbers().stream()
                .map(LottoNumber::value)
                .collect(Collectors.toList()));
        }
    }

    public static void showWinningResult(Map<LottoReward, Integer> results) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        List<LottoReward> rewards = Arrays.asList(LottoReward.values());
        Collections.reverse(rewards);
        for (LottoReward reward : rewards) {
            printLottoReward(reward, results.get(reward));
        }
    }

    private static void printLottoReward(LottoReward reward, int rewardCount) {
        if (reward == LottoReward.NONE) {
            return;
        }
        if (reward.hasBonus()) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", reward.matchCount(), reward.price(), rewardCount);
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개\n", reward.matchCount(), reward.price(), rewardCount);
    }

    public static void showProfitRate(double profitRate) {
        double processedProfitRate = Math.floor(profitRate * SECOND_DECIMAL_POINT) / SECOND_DECIMAL_POINT;
        if (profitRate < BENEFIT_STANDARD) {
            System.out.printf("총 수익률은 (%.2f)입니다.(기준이 %d이기 때문에 결과적으로 손해라는 의미임)\n",
                processedProfitRate, BENEFIT_STANDARD);
            return;
        }
        if (profitRate >= BENEFIT_STANDARD) {
            System.out.printf("총 수익률은 (%.2f)입니다.", processedProfitRate);
        }
    }
}
