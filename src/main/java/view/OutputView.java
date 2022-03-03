package view;

import java.util.List;
import java.util.StringJoiner;
import dto.RankResultDto;
import dto.LottoDto;
import model.rank.Rank;

public class OutputView {
    private static final String PURCHASE_LOTTO_COUNT_PRINT_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String RIGHT_COVER = "[";
    private static final String LEFT_COVER = "]";
    private static final String JOINING_DELIMITER = ", ";
    private static final String WINNING_STATISTICS_MESSAGE = "\n당첨 통계\n---------";
    private static final String NOT_BONUS_MATCH_RANK_PRINT_FORMAT = "%d개 일치 (%d원)- %d개\n";
    private static final String BONUS_MATCH_RANK_PRINT_FORMAT = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    private static final String NO_PRINT_FORMAT = "";
    private static final String RATE_OF_RETURN_PRINT_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n";

    public void printTotalPurchaseLottoCount(int manualLottoCount, int autoLottoCount) {
        System.out.printf(PURCHASE_LOTTO_COUNT_PRINT_FORMAT, manualLottoCount, autoLottoCount);
    }

    public void printTotalLottos(final List<LottoDto> lottoDtos) {
        lottoDtos.forEach(this::printLotto);
    }

    private void printLotto(final LottoDto lottoDto) {
        System.out.print(RIGHT_COVER);
        printNumbers(lottoDto.getLottoNumbers());
        System.out.println(LEFT_COVER);
    }

    private void printNumbers(final List<Integer> numbers) {
        StringJoiner stringJoiner = new StringJoiner(JOINING_DELIMITER);
        numbers.forEach(number -> stringJoiner.add(String.valueOf(number)));
        System.out.print(stringJoiner);
    }

    public void printWinningResult(final List<RankResultDto> WinningResult) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        for (RankResultDto rankResultDto : WinningResult) {
            printRankResult(rankResultDto);
        }
    }

    private void printRankResult(final RankResultDto rankResultDto) {
        System.out.printf(printRankResultFormat(rankResultDto.getRank()), rankResultDto.getMatchCount(),
                rankResultDto.getRankPrize(), rankResultDto.getWinningCount());
    }

    private String printRankResultFormat(final Rank rank) {
        if (rank == Rank.SECOND) {
            return BONUS_MATCH_RANK_PRINT_FORMAT;
        }
        if (rank == Rank.NONE) {
            return NO_PRINT_FORMAT;
        }
        return NOT_BONUS_MATCH_RANK_PRINT_FORMAT;
    }

    public void printRateOfReturn(double rateOfReturn) {
        System.out.printf(RATE_OF_RETURN_PRINT_FORMAT, rateOfReturn);
    }
}
