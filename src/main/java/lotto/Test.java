package lotto;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
	LottoService service;

	public Test() {
		service = null;
	}

	public void injectMoney(int money) {
		service = new LottoService(money);
	}

	public void buy(Lotto lotto) {
		service.buy(lotto);
	}

	public int assignAutoPurchaseCount() {
		RandomNumbersGenerator generator = RandomNumbersGenerator.getInstance();
		LottoFactory lottoFactory = new LottoFactory();
		int autoPurchaseCount = 0;
		for (; service.canBuy(); autoPurchaseCount++) {
			Lotto lotto = lottoFactory.create(generator.generate());
			service.buy(lotto);
		}
		return autoPurchaseCount;
	}

	public List<Lotto> getLottos() {
		return service.getLottos();
	}

	public LottoGameResult gameResult() {
		return service.gameResult();
	}

	public List<String> showGameResult(final LottoGameResult gameResult) {
		List<String> results = new ArrayList<>();

		for (Rank rank : Rank.reverseValues()) {
			results.add(showRank(rank) + gameResult.getRankCount(rank) + "개");
		}
		return results;
	}

	private String showRank(final Rank rank) {
		StringBuilder sb = new StringBuilder();
		sb.append(rank.getMatchCount() + "개 일치");
		if (rank == Rank.SECOND) {
			sb.append(", 보너스 볼 일치");
		}
		sb.append("(" + rank.getMoney() + ")- ");
		return sb.toString();
	}
}
