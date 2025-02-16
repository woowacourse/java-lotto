package view;

import domain.WinningCase;
import dto.BuyLottoResultDto;
import dto.DrawResultDto;
import exception.LottoException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private final static String BUY_LOTTO_MONEY = "구입금액을 입력해 주세요.";
    private final static String INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private final static String BUY_LOTTO_AMOUNT_FORMAT = "%d개를 구매했습니다.\n";
    private static final String WINNING_CALCULATE_START = "\n당첨 통계\n--------\n";
    private static final String EARN_MONEY_RATIO = "총 수익률은 %f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String BASIC_RANK_NOTICE = "%d개 일치 (%d원)- %d개";
    private static final String SPECIAL_RANK_NOTICE = "%d개 일치, 보너스 볼 일치 (%d원) - %d개";

    public static void printBuyLottoMoney() {
        printLine(BUY_LOTTO_MONEY);
    }

    public static void printError(LottoException lottoException) {
        printLine(lottoException.getMessage());
    }

    public static void printInputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER);
    }

    public static void printInputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
    }

    public static void printBuyLottos(BuyLottoResultDto buyLottoResult) {
        System.out.println(String.format(BUY_LOTTO_AMOUNT_FORMAT, buyLottoResult.amount()));
        for (List<Integer> buyLotto : buyLottoResult.buyLottos()) {
            System.out.println(buyLotto);
        }
    }

    public static void printDrawResult(DrawResultDto drawResultDto) {
        Map<WinningCase, Integer> winningResult = drawResultDto.drawResult();
        StringBuilder stringBuilder = new StringBuilder(WINNING_CALCULATE_START);
        winningResult.remove(WinningCase.ELSE);
        for (Entry<WinningCase, Integer> winningCaseIntegerEntry : winningResult.entrySet()) {
            WinningCase winningCase = winningCaseIntegerEntry.getKey();
            int count = winningCaseIntegerEntry.getValue();
            stringBuilder.append(formatDrawResult(winningCase,count))
                .append("\n");
        }
        stringBuilder.append(String.format(EARN_MONEY_RATIO,drawResultDto.earnMoneyRatio()));
        System.out.println(stringBuilder);
    }

    private static String formatDrawResult(WinningCase winningCase, int count) {
        return String.format(selectPattern(winningCase),
            winningCase.getSameCount(),
            winningCase.getWinningMoney(),
            count
        );
    }

    private static String selectPattern(WinningCase winningCase) {
        if (winningCase == WinningCase.FIVE_BONUS_SAME) {
            return SPECIAL_RANK_NOTICE;
        }
        return BASIC_RANK_NOTICE;
    }

    private static void printLine(String message) {
        System.out.println(message);
    }

}
