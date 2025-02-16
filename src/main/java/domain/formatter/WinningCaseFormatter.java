package domain.formatter;

public class WinningCaseFormatter {

    private static final String WINNING_CASE_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final String FIVE_BONUS_SAME_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원) - %d개";

    public static String formatting(int money, int sameCount, int matchCount, boolean isFiveBonusSame) {
        if (isFiveBonusSame) {
            return String.format(FIVE_BONUS_SAME_FORMAT, sameCount, money, matchCount);
        }
        return String.format(WINNING_CASE_FORMAT, sameCount, money, matchCount);
    }
}
