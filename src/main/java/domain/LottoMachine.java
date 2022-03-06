package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
	private final Money money;
	private final List<LottoTicket> lottoTickets;

	public LottoMachine(int money, List<List<Integer>> totalManualNumbers) {
		this.money = new Money(money);
		this.lottoTickets = new ArrayList<>();
		lottoTickets.addAll(purchaseByManual(totalManualNumbers));
		lottoTickets.addAll(purchaseByRandom(this.money.calculateTotalCount() - totalManualNumbers.size()));
	}

	public Result generateResult(AnswerLotto answerLotto) {
		List<ResultStatics> totalResults =
			lottoTickets.stream()
				.map(l -> l.calculate(answerLotto))
				.collect(Collectors.toList());

		return new Result(totalResults, money.getValue());
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(lottoTickets);
	}

	private static List<LottoTicket> purchaseByManual(List<List<Integer>> totalManualNumbers) {
		List<LottoTicket> purchasedLottoTickets = new ArrayList<>();
		for (List<Integer> lottoNumbers : totalManualNumbers) {
			purchasedLottoTickets.add(LottoTicket.byManual(lottoNumbers));
		}
		return purchasedLottoTickets;
	}

	private static List<LottoTicket> purchaseByRandom(int randomCount) {
		List<LottoTicket> purchasedLottoTickets = new ArrayList<>();
		while (randomCount-- > 0) {
			purchasedLottoTickets.add(LottoTicket.byRandom());
		}
		return purchasedLottoTickets;
	}
}
