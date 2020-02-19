package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
	private List<Lotto> lotteries = new ArrayList<>();

	// public long compareTicket(Lotto lotto, Lotto winLotto) {
	// 	return lotto.stream()
	// 		.filter(x -> winLotto.numbers.contains(x))
	// 		.count();
	// }

	public LottoManager(int count) {
		this.lotteries = LottoFactory.createLotteries(count);
	}
}
