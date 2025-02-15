package lotto.view;

import lotto.constant.WinningTier;
import lotto.domain.Lotto;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class OutputView {

    private void printWinningStatistics(List<WinningTier> winningTiers) {
        List<WinningTier> tiers = Arrays.stream(WinningTier.values())
                .toList()
                .reversed();

        for (WinningTier tier : tiers) {
            printTier(winningTiers, tier);
        }
    }

    private void printTier(List<WinningTier> targets, WinningTier tier) {
        if (tier == WinningTier.EMPTY) {
            return;
        }

        long count = targets.stream()
                .filter(winningTier -> winningTier == tier)
                .count();

        if (tier.getHasBonusMatch()) {
            printBonusTier(tier, count);
            return;
        }
        printRegularTier(tier, count);
    }

    private void printBonusTier(WinningTier tier, long count) {
        String template = "%d개 일치, 보너스 볼 일치 (%d원) - %d개";
        String content = String.format(template, tier.getMatches(), tier.getPrize(), count);
        System.out.println(content);
    }

    private void printRegularTier(WinningTier tier, long count) {
        String template = "%d개 일치 (%d원) - %d개";
        String content = String.format(template, tier.getMatches(), tier.getPrize(), count);
        System.out.println(content);
    }

    private void printProfit(double profit) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        String formattedProfit = formatter.format(profit);
        String content = String.format("총 수익률은 %s입니다.", formattedProfit);
        if (profit < 1) {
            content += "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        System.out.println(content);
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printLottos(List<Lotto> lottos) {
        String content = String.format("%d개를 구매했습니다.", lottos.size());
        System.out.println(content);
        for (Lotto lotto : lottos) {
            String lottoNumbers = lotto.getNumbers().toString();
            System.out.println(lottoNumbers);
        }
        this.printBlankLine();
    }

    public void printResults(List<WinningTier> winningTiers, double profit) {
        System.out.println("당첨 통계\n---------");
        this.printWinningStatistics(winningTiers);
        this.printProfit(profit);
        this.printBlankLine();
    }
}
