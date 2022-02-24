package lotto.view.output;

import lotto.dto.LottoMatchKindDto;
import lotto.dto.LottoNumbersDto;

import java.util.List;
import java.util.StringJoiner;

public class ConsoleOutputView implements OutputView {
    private static final String OUTPUT_PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String LOTTO_NUMBER_SEPARATOR = ", ";
    private static final String LOTTO_NUMBERS_PREFIX = "[";
    private static final String LOTTO_NUMBERS_SUFFIX = "]";
    private static final String WINNING_STATISTICS_MESSAGE = "\n당첨 통계\n---------";
    private static final String WINNING_STATISTICS_MESSAGE_FORMAT = "%d개 일치 (%d원) - %d개\n";
    private static final String WINNING_STATISTICS_MESSAGE_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개\n";
    private static final String PROFIT_RATE_MESSAGE_FORMAT = "총 수익률은 %.2f입니다.";

    @Override
    public void printPurchaseCount(final int purchaseCount) {
        System.out.println(purchaseCount + OUTPUT_PURCHASE_COUNT_MESSAGE);
    }

    @Override
    public void printLottoNumbersGroup(final List<LottoNumbersDto> lottoNumbersGroup) {
        lottoNumbersGroup.forEach(this::printOneOfLottoNumbersGroup);
        System.out.println();
    }

    private void printOneOfLottoNumbersGroup(final LottoNumbersDto lottoNumbers) {
        final StringJoiner result =
                new StringJoiner(LOTTO_NUMBER_SEPARATOR, LOTTO_NUMBERS_PREFIX, LOTTO_NUMBERS_SUFFIX);
        final List<Integer> numbers = lottoNumbers.getLottoNumbers();
        numbers.forEach(number -> result.add(String.valueOf(number)));
        System.out.println(result);
    }

    @Override
    public void printCountOfWinningByMatchKind(final List<LottoMatchKindDto> winningResults) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        winningResults.forEach(this::printEachCountOfWinningByMatchKind);
    }

    private void printEachCountOfWinningByMatchKind(final LottoMatchKindDto winningResult) {
        System.out.printf(findMessageFormatByBonus(winningResult),
                winningResult.getMatchCount(), winningResult.getWinningAmount(), winningResult.getWinningCount());
    }

    private String findMessageFormatByBonus(LottoMatchKindDto winningResult) {
        if (winningResult.hasMatchedBonus()) {
            return WINNING_STATISTICS_MESSAGE_BONUS_FORMAT;
        }
        return WINNING_STATISTICS_MESSAGE_FORMAT;
    }

    @Override
    public void printProfitRate(final double profitRate) {
        System.out.printf(PROFIT_RATE_MESSAGE_FORMAT, profitRate);
    }
}
