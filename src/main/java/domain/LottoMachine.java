package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
	private Money money;
	private List<LottoTicket> lottoTickets;

	public LottoMachine(int money) {
		this.money = new Money(money);
		lottoTickets = new ArrayList<>();
	}

	public void purchase(List<List<Integer>> totalManualNumbers) {
		purchaseByManual(totalManualNumbers);
		purchaseByRandom(money.calculateTotalCount() - lottoTickets.size());
	}

	public Result generateResult(AnswerLotto answerLotto) {
		List<ResultStatics> totalResults =
			lottoTickets.stream()
				.map(l -> l.calculate(answerLotto))
				.collect(Collectors.toList());

		return new Result(totalResults,money.getValue());
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(lottoTickets);
	}

	private void purchaseByManual(List<List<Integer>> totalManualNumbers) {
		for (List<Integer> lottoNumbers : totalManualNumbers) {
			lottoTickets.add(LottoTicket.byManual(lottoNumbers));
		}
	}

	private void purchaseByRandom(int randomCount) {
		while (randomCount-- > 0) {
			lottoTickets.add(LottoTicket.byRandom());
		}
	}
}
