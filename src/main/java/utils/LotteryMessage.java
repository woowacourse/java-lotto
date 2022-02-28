package utils;

import controller.dto.RankDto;
import domain.Rank;

public class LotteryMessage {

	private static final String LOTTERY_NUMBER = "로또번호";
	private static final String WINNING_NUMBER = "당첨번호";
	private static final String BONUS_BALL = "보너스볼";
	private static final String PURCHASE_AMOUNT = "구입 금액";

	// error message
	public static final String LOTTERY_SIZE_ERROR = LOTTERY_NUMBER + "는 6개여야 합니다.";
	public static final String LOTTERY_RANGE_ERROR = "각 " + LOTTERY_NUMBER + "는 1~45 사이여야 합니다";
	public static final String BONUS_BALL_RANGE_ERROR = BONUS_BALL + "의 번호는 1~45 사이여야 합니다";
	public static final String DUPLICATED_WINNING_NUMBER_WITH_BONUS_NUMBER = WINNING_NUMBER + "와 " + BONUS_BALL
		+ "에 중복된 번호가 있으면 안됩니다.";
	public static final String PURCHASE_AMOUNT_RANGE_ERROR = PURCHASE_AMOUNT + "의 범위는 1000원~100000원 입니다.";
	public static final String PURCHASE_AMOUNT_NOT_NUMBER_ERROR = PURCHASE_AMOUNT + "은 숫자여야 합니다.";
	public static final String WINNING_NUMBER_FORMAT_ERROR = WINNING_NUMBER + "는 \"1,2,3,4,5,6\"과 같은 꼴 이어야 합니다";

	// input message
	public static final String INPUT_PURCHASE_AMOUNT = PURCHASE_AMOUNT + "을 입력해 주세요";
	public static final String LAST_WEEK_WINNING_NUMBERS = "지난 주 " + WINNING_NUMBER + "를 입력해 주세요.";
	public static final String LAST_WEEK_BONUS_NUMBER = BONUS_BALL + "을 입력해 주세요.";

	// output message
	public static final String WINNING_STATISTICS = "당첨 통계\n--------\n";
	public static final String TOTAL_EARNING_RATE = "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

	public static String makeRankingMessage (final RankDto rank, final int winningCount) {
		String rakingMessage = rank.getCorrectedBalls() + "개 일치";
		if(rank.getRankName().equals("SECOND")) {
			rakingMessage += ", " + BONUS_BALL + " 일치";
		}
		return rakingMessage + " (" + rank.getPrize() + "원) - "
			+ winningCount + "개";
	}
}
