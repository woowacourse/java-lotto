package lotto.service;

import lotto.costant.OutputMessage;
import lotto.costant.WinningTier;
import lotto.domain.Lotto;
import lotto.domain.WinningCondition;
import lotto.view.OutputView;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class OutputService {

    private final OutputView outputView;

    public OutputService(OutputView outputView) {
        this.outputView = outputView;
    }

    public void printLottos(List<Lotto> lottos) {
        String content = String.format(OutputMessage.PURCHASE_COMPLETE.getContent(), lottos.size());
        outputView.printLine(content);
        for (Lotto lotto : lottos) {
            String lottoNumbers = lotto.getNumbers().toString();
            outputView.printLine(lottoNumbers);
        }
        outputView.printBlankLine();
    }

    public void printResults(List<WinningTier> winningTiers, double profit) {
        outputView.printLine(OutputMessage.RESULTS_HEADER.getContent());
        printWinningStatistics(winningTiers);
        printProfit(profit);
        outputView.printBlankLine();
    }

    private void printWinningStatistics(List<WinningTier> winningTiers) {
        List<WinningTier> tiers = Arrays.stream(WinningTier.values()).toList();

        for (WinningTier tier : tiers) {
            if (tier == WinningTier.EMPTY) {
                continue;
            }

            long count = winningTiers.stream().filter(winningTier -> winningTier == tier).count();
            WinningCondition winningCondition = tier.getCondition();

            if (winningCondition.isBonusMatchNeeded()) {
                String template = OutputMessage.BONUS_TIER.getContent();
                String content = String.format(template, winningCondition.getMatchedCount(), tier.getPrize(), count);
                outputView.printLine(content);
                continue;
            }

            String template = OutputMessage.REGULAR_TIER.getContent();
            String content = String.format(template, winningCondition.getMatchedCount(), tier.getPrize(), count);
            outputView.printLine(content);
        }
    }

    private void printProfit(double profit) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        String formattedProfit = formatter.format(profit);
        String content = String.format(OutputMessage.PROFIT.getContent(), formattedProfit);
        if (profit < 1) {
            content += OutputMessage.LOSS_DESCRIPTION.getContent();
        }
        outputView.printLine(content);
    }
}
