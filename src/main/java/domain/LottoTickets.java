package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
	private final List<LottoTicket> lottoTickets = new ArrayList<>();

	public void purchase(LottoTicket lottoTicket) {
		lottoTickets.add(lottoTicket);
	}

	public void purchase(List<LottoNumbers> lottoNumbers) {
		for (LottoNumbers eachLottoNumbers : lottoNumbers) {
			lottoTickets.add(LottoTicket.byManual(eachLottoNumbers));
		}
	}

	public Result generateResult(AnswerLotto answerLotto) {
		Result results = new Result();
		for (LottoTicket lotto : lottoTickets) {
			results.addResult(lotto.calculate(answerLotto));
		}

		return results;
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(lottoTickets);
	}

}
