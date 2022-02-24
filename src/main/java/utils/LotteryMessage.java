package utils;

import domain.Rank;

public class LotteryMessage {

	public static final String LOTTERY_SIZE_ERROR = "로또 번호는 6개여야 합니다.";
	public static final String LOTTERY_RANGE_ERROR = "로또의 각 번호는 1~45 사이여야 합니다";
	public static final String BONUS_BALL_RANGE_ERROR = "보너스볼의 번호는 1~45 사이여야 합니다";
	public static final String DUPLICATED_WINNING_NUMBER_WITH_BONUS_NUMBER = "당첨번호와 보너스볼에 중복된 번호가 있으면 안됩니다.";
	public static final String PURCHASE_AMOUNT_RANGE_ERROR = "구입 금액의 범위는 1000원~100000원 입니다.";
	public static final String PURCHASE_AMOUNT_NOT_NUMBER_ERROR = "구입 금액은 숫자여야 합니다.";
	public static final String WINNING_NUMBER_FORMAT_ERROR = "당첨번호는 \"1,2,3,4,5,6\"과 같은 꼴 이어야 합니다";

	public static final String PURCHASE_AMOUNT = "구입금액을 입력해 주세요";
	public static final String LAST_WEEK_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
	public static final String LAST_WEEK_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

	public static final String WINNING_STATISTICS = "당첨 통계\n--------\n";
	public static final String TOTAL_EARNING_RATE = "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

	public static String makeRankingMessage (final Rank rank, final int winningCount) {
		String rakingMessage = rank.getCorrectedBalls() + "개 일치";
		if(rank == Rank.SECOND) {
			rakingMessage += ", 보너스 볼 일치";
		}
		return rakingMessage + " (" + rank.getPrize() + "원) - "
			+ winningCount + "개";
	}
}
