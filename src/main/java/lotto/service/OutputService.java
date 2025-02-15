package lotto.service;

import lotto.constant.OutputMessage;
import lotto.constant.WinningTier;
import lotto.domain.Lotto;
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
        List<WinningTier> tiers = Arrays.stream(WinningTier.values()).toList().reversed();
        for (WinningTier tier : tiers) {
            printTier(winningTiers, tier);
        }
    }

    private void printTier(List<WinningTier> targets, WinningTier tier) {
        if (tier == WinningTier.EMPTY) {
            return;
        }

        long count = targets.stream().filter(winningTier -> winningTier == tier).count();
        if (tier.getHasBonusMatch()) {
            printBonusTier(tier, count);
            return;
        }
        printRegularTier(tier, count);
    }

    private void printBonusTier(WinningTier tier, long count) {
        String template = OutputMessage.BONUS_TIER.getContent();
        String content = String.format(template, tier.getMatches(), tier.getPrize(), count);
        outputView.printLine(content);
    }

    private void printRegularTier(WinningTier tier, long count) {
        String template = OutputMessage.REGULAR_TIER.getContent();
        String content = String.format(template, tier.getMatches(), tier.getPrize(), count);
        outputView.printLine(content);
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
