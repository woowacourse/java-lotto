package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.model.number.BonusBall;
import lotto.model.number.WinningBalls;
import lotto.model.prize.MatchResult;

public class Lottos {
	private static final int PRICE = 1000;
	private static final String ERROR_BOUND = "[ERROR] 로또 개수는 0 이상, 총 구매 개수 이하로 입력해 주세요";

	private final List<Lotto> lottos;

	private Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public static Lottos purchase(Money money, int manualCount, List<String[]> manualLottoNumbers) {
		int totalCount = countTickets(money);
		checkBound(totalCount, manualCount);
		int autoCount = totalCount - manualCount;

		List<Lotto> lottos = new ArrayList<>();
		generateLottos(autoCount, manualLottoNumbers, lottos);

		return new Lottos(lottos);
	}

	private static void checkBound(int totalCount, int manualCount) {
		if (manualCount < 0 || manualCount > totalCount) {
			throw new IllegalArgumentException(ERROR_BOUND);
		}
	}

	private static void generateLottos(int autoCount, List<String[]> input, List<Lotto> lottos) {
		generateManualLotto(input, lottos);
		generateAutoLotto(autoCount, lottos);
	}

	private static void generateManualLotto(List<String[]> input, List<Lotto> lottos) {
		for (String[] numbers : input) {
			lottos.add(new Lotto(Lotto.generateManual(numbers)));
		}
	}

	private static void generateAutoLotto(int autoCount, List<Lotto> lottos) {
		for (int index = 0; index < autoCount; index++) {
			lottos.add(new Lotto(Lotto.generateAuto()));
		}
	}

	private static int countTickets(Money money) {
		return money.countAvailable(PRICE);
	}

	public List<MatchResult> match(WinningBalls winningBalls, BonusBall bonusBall) {
		return this.lottos.stream()
				.map(lotto -> MatchResult.of(lotto, winningBalls, bonusBall))
				.collect(Collectors.toList());
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}
}
