package view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Lotto;
import domain.LottoRank;
import domain.Number;

public class OutputView {

    private static final String SECOND_RANK_RESULT_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String OTHERS_RANK_RESULT_MESSAGE = "%d개 일치 (%d원) - %d개";
    private static final String PROFIT_RESULT_MESSAGE = "총 수익률은 %.2f입니다.";
    private static final String RESULT_TITLE_MESSAGE = "당첨 통계\n---------";
    private static final String REQUEST_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String PURCHASE_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String REQUEST_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String REQUEST_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String REQUEST_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void printNewLine() {
        System.out.println();
    }

    public void printRequestMoney() {
        printMessage(REQUEST_MONEY_MESSAGE);
    }

    public void printPurchasedLottoTicket(int manualLottoQuantity, int autoLottoQuantity, List<Lotto> lottoTicket) {
        printNewLine();
        printMessage(String.format(PURCHASE_COUNT_MESSAGE, manualLottoQuantity, autoLottoQuantity));
        for (Lotto lotto : lottoTicket) {
            printLottoNumber(sortLottoNumbers(lotto.getLotto()));
        }
    }

    private List<Number> sortLottoNumbers(List<Number> lotto) {
        lotto.sort(Comparator.comparingInt(Number::getNumber));
        return lotto;
    }

    private void printLottoNumber(List<Number> lottoNumbers) {
        int[] printingLottoNumbers = lottoNumbers.stream()
                .mapToInt(Number::getNumber)
                .toArray();

        printMessage(Arrays.toString(printingLottoNumbers));
    }

    public void printRequestWinningNumbers() {
        printNewLine();
        printMessage(REQUEST_WINNING_NUMBERS_MESSAGE);
    }

    public void printRequestBonusNumber() {
        printMessage(REQUEST_BONUS_NUMBER_MESSAGE);
    }

    public void printWinningResult(Map<LottoRank, Integer> winningResult) {
        printNewLine();
        printMessage(RESULT_TITLE_MESSAGE);

        Set<LottoRank> ranks = winningResult.keySet();
        for (LottoRank rank : ranks) {
            printMessage(createRankMessage(rank, winningResult.get(rank)));
        }
    }

    private String createRankMessage(LottoRank rank, int count) {
        if (rank == LottoRank.SECOND) {
            return String.format(SECOND_RANK_RESULT_MESSAGE, rank.getMatchCount(), rank.getAmount(), count);
        }
        return String.format(OTHERS_RANK_RESULT_MESSAGE, rank.getMatchCount(), rank.getAmount(), count);
    }

    public void printRateOfProfit(double rateOfProfit) {
        printMessage(String.format(PROFIT_RESULT_MESSAGE, rateOfProfit));
    }

    public void printRequestManualLottoCount() {
        printNewLine();
        printMessage(REQUEST_MANUAL_LOTTO_COUNT);
    }

    public void printRequestManualLottoNumbers() {
        printNewLine();
        printMessage(REQUEST_MANUAL_LOTTO_NUMBERS);
    }
}
