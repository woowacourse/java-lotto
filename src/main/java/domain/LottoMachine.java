package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		Result result = new Result();

		for (LottoTicket lottoTicket : lottoTickets) {
			result.addResult(lottoTicket.calculate(answerLotto));
		}

		result.calculateProfitRate(money.getValue());

		return result;
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(lottoTickets);
	}

	private void purchaseByManual(List<List<Integer>> totalManualNumbers) {
		for (List<Integer> lottoNumbers : totalManualNumbers) {
			lottoTickets.add(LottoTicket.byManual(new LottoNumbers(lottoNumbers)));
		}
	}

	private void purchaseByRandom(int randomCount) {
		while (randomCount-- > 0) {
			lottoTickets.add(LottoTicket.byRandom());
		}
	}
}
