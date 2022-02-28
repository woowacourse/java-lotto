package lotto.view;

import lotto.domain.ChoiceNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.LottoDto;

public class OutputView {

    private static final String PURCHASE_AMOUNT_MESSAGE = "%d개를 구매했습니다.%n";
    private static final String WINNING_STATISTIC_MESSAGE = "\n당첨 통계\n---------\n";
    private static final String WINNING_STATISTIC_FORMAT = "%d개 일치 %s(%d원) - %d개";
    private static final String BONUS_BALL_MESSAGE = ", 보너스 볼 일치";
    private static final String EMPTY_STRING = "";
    private static final String YIELD_MESSAGE_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)%n";

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printLotto(LottoDto lottoDto) {
        System.out.printf(PURCHASE_AMOUNT_MESSAGE, lottoDto.getLottoSize());
        for (ChoiceNumber choiceNumber : lottoDto.getLotto()) {
            System.out.println(choiceNumber);
        }
    }

    public static void printResult(LottoResult result) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(WINNING_STATISTIC_MESSAGE);
        for (LottoRank lottoRank : result.getResult().keySet()) {
            String message = String.format(WINNING_STATISTIC_FORMAT, lottoRank.getCorrectNumber(),
                    getBonusMessage(lottoRank), lottoRank.getPrizeAmount(),
                    result.getResult().get(lottoRank));
            stringBuilder.append(message).append("\n");
        }
        System.out.print(stringBuilder);
    }

    private static String getBonusMessage(LottoRank lottoRank) {
        if (lottoRank == LottoRank.SECOND) {
            return BONUS_BALL_MESSAGE;
        }
        return EMPTY_STRING;
    }

    public static void printYield(double yield) {
        System.out.printf(YIELD_MESSAGE_FORMAT, yield);
    }
}
