package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoCount;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.money.MoneyForLotto;
import lotto.domain.result.Rank;
import lotto.domain.result.ResultStatistic;

/**
 * 콘솔로 출력한다
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class OutputView {
	private static final String ENTER_MONEY_FOR_LOTTO_GUIDE_MESSAGE = "구매금액을 입력해 주세요.";
	private static final String PURCHASED_LOTTO_NUMBER_IS = "수동으로 %d장, 자동으로 %d장을 구매했습니다.\n";
	private static final String ENTER_WINNING_LOTTO_GUIDE_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String ENTER_BONUS_BALL_NUMBER_GUIDE_MESSAGE = "보너스 볼을 입력해 주세요.";
	private static final String RESULT_STATISTIC_MESSAGE = "\n당첨 통계\n--------";
	private static final String LOTTO_DELIMITER = ", ";
	private static final String LOTTO_PREFIX = "[";
	private static final String LOTTO_SUFFIX = "]";
	private static final String TOTAL_REVENUE_RATE_IS = "총 수익률은 %d%%입니다.\n";
	private static final String WINNING_RESULT_MESSAGE = "%d개 일치 (%d원)- %d개\n";
	private static final String SECOND_WINNING_RESULT_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원) - %d개\n";
	private static final String ENTER_MANUAL_LOTTO_COUNT_GUIDE_MESSAGE = "수동으로 구매할 로또 수를 입력해주세요.";
	private static final String ENTER_MANUAL_LOTTO_NUMBERS_GUIDE_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

	public static void askEnterMoneyForLotto() {
		System.out.println(ENTER_MONEY_FOR_LOTTO_GUIDE_MESSAGE);
	}

	public static void printExceptionMessage(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void printPurchasedLottos(LottoCount totalLottoCount, LottoCount manualLottoCount,Lottos lottos) {
		System.out.printf(PURCHASED_LOTTO_NUMBER_IS, manualLottoCount.getLottoCount(), totalLottoCount.getLottoCount() - manualLottoCount.getLottoCount());

		List<String> purchasedLottos = new ArrayList<>();
		for (Lotto lotto : lottos.getLottos()) {
			purchasedLottos.add(lotto.getLottoNumbers().stream()
					.map(LottoNumber::getLottoNumber)
					.map(Object::toString)
					.collect(Collectors.joining(LOTTO_DELIMITER, LOTTO_PREFIX, LOTTO_SUFFIX))
			);
		}
		purchasedLottos.forEach(System.out::println);
	}

	public static void askManualLottoCount() {
		System.out.println(ENTER_MANUAL_LOTTO_COUNT_GUIDE_MESSAGE);
	}

	public static void askEnterWinningLotto() {
		System.out.println(ENTER_WINNING_LOTTO_GUIDE_MESSAGE);
	}

	public static void askEnterBonusLottoNumber() {
		System.out.println(ENTER_BONUS_BALL_NUMBER_GUIDE_MESSAGE);
	}

	public static void printResultStatistic(ResultStatistic result, MoneyForLotto money) {
		System.out.println(RESULT_STATISTIC_MESSAGE);
		Rank.getReversedProfitableValues()
				.forEach(rank -> printEachRankResult(rank, result));
		System.out.printf(TOTAL_REVENUE_RATE_IS, result.calculateRevenueRate(money));
	}

	private static void printEachRankResult(Rank rank, ResultStatistic result) {
		String resultMessage = WINNING_RESULT_MESSAGE;
		if (rank.equals(Rank.SECOND)) {
			resultMessage = SECOND_WINNING_RESULT_MESSAGE;
		}
		System.out.printf(resultMessage, rank.getMatchCounts(), rank.getReward(), result.getResultCount(rank));
	}

	public static void askEnterManualLottoNumbers() {
		System.out.println(ENTER_MANUAL_LOTTO_NUMBERS_GUIDE_MESSAGE);
	}
}
