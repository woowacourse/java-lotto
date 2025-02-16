package lotto.view;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.WinningCondition;
import lotto.domain.WinningTier;
import lotto.view.message.OutputMessage;

public class OutputView {

    private final static String DECIMAL_FORMATTER_PATTERN = "0.00";
    private final static int PROFIT_BOUNDARY = 1;
    private final DecimalFormat decimalFormatter;

    public OutputView() {
        this.decimalFormatter = new DecimalFormat(DECIMAL_FORMATTER_PATTERN);
    }

    public void printLottos(List<Lotto> lottos) {
        String content = String.format(OutputMessage.PURCHASE_COMPLETE.getContent(), lottos.size());
        printLine(content);
        lottos.forEach(lotto -> printLine(lotto.getNumbers().toString()));
        printBlankLine();
    }

    public void printResults(List<WinningTier> winningTiers, double profit) {
        printLine(OutputMessage.RESULTS_HEADER.getContent());
        printWinningStatistics(winningTiers);
        printProfit(profit);
        printBlankLine();
    }

    private void printWinningStatistics(List<WinningTier> winningTiers) {
        List<WinningTier> tiers = Arrays.stream(WinningTier.values()).toList().reversed();
        tiers.forEach(tier -> printTier(winningTiers, tier));
    }

    private void printTier(List<WinningTier> targets, WinningTier tier) {
        if (tier == WinningTier.EMPTY) {
            return;
        }

        long count = targets.stream().filter(winningTier -> winningTier == tier).count();
        if (tier.getCondition().isBonusMatchNeeded()) {
            printBonusTier(tier, count);
            return;
        }
        printRegularTier(tier, count);
    }

    private void printBonusTier(WinningTier tier, long count) {
        WinningCondition winningCondition = tier.getCondition();
        String template = OutputMessage.BONUS_TIER.getContent();
        String content = String.format(template, winningCondition.getMatchedCount(), tier.getPrize(), count);
        printLine(content);
    }

    private void printRegularTier(WinningTier tier, long count) {
        WinningCondition winningCondition = tier.getCondition();
        String template = OutputMessage.REGULAR_TIER.getContent();
        String content = String.format(template, winningCondition.getMatchedCount(), tier.getPrize(), count);
        printLine(content);
    }

    private void printProfit(double profit) {
        String formattedProfit = decimalFormatter.format(profit);
        String content = String.format(OutputMessage.PROFIT.getContent(), formattedProfit);
        if (profit < PROFIT_BOUNDARY) {
            content += OutputMessage.LOSS_DESCRIPTION.getContent();
        }
        printLine(content);
    }

    private void printLine(String content) {
        System.out.println(content);
    }

    private void printBlankLine() {
        System.out.println();
    }
}
