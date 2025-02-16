package view;

import dto.LottoDto;
import dto.LottoResultDto;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import model.Rank;

public class OutputView {
    private static final String PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_GUIDE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String WINNING_BONUS_GUIDE = "보너스 볼을 입력해 주세요.";
    private static final String RESULT_GUIDE = "\n당첨 통계\n---------";
    private static final String SECOND_RANK_RESULT_FORM = "5개 일치, 보너스 볼 일치(30000000원) - %d개\n";
    private static final String PURCHASE_LOTTO_NUMBER_GUIDE_FORM = "%d개를 구매했습니다.\n";
    private static final String RANK_RESULT_FORM = "%d개 일치 (%d원)- %d개\n";
    private static final String PROFIT_RATE_RESULT_FORM = "총 수익률은 %.2f입니다.\n";

    public void printPurchaseAmountGuide() {
        System.out.println(PURCHASE_AMOUNT_GUIDE);
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printPurchaseLottos(List<LottoDto> lottosDto) {
        System.out.printf(PURCHASE_LOTTO_NUMBER_GUIDE_FORM, lottosDto.size());
        for (LottoDto lottoDto : lottosDto) {
            List<String> sortedList = lottoDto.lotto().stream().sorted().map(String::valueOf).toList();
            printLotto(sortedList);
        }
        System.out.println();
    }

    private void printLotto(List<String> lotto) {
        System.out.println("[" + String.join(", ", lotto) + "]");
    }

    public void printWinningNumberGuide() {
        System.out.println(WINNING_NUMBER_GUIDE);
    }

    public void printBonusGuide() {
        System.out.println(WINNING_BONUS_GUIDE);
    }

    public void printResultRanks(LottoResultDto lottoResultDto) {
        System.out.println(RESULT_GUIDE);
        EnumMap<Rank, Integer> rankResult = lottoResultDto.rankResult();
        rankResult.keySet()
                .forEach(rank -> {
                    if (rank.equals(Rank.SECOND)) {
                        System.out.printf(SECOND_RANK_RESULT_FORM, rankResult.get(rank));
                    }
                    if (!rank.equals(Rank.FAIL)) {
                        System.out.printf(RANK_RESULT_FORM, rank.getMatchNumber(), rank.getWinningAmount(), rankResult.get(rank));
                    }
                });
        printProfitRate(lottoResultDto.profitRate());
    }

    public void printProfitRate(double profitRate) {
        System.out.printf(PROFIT_RATE_RESULT_FORM, profitRate);
    }
}
