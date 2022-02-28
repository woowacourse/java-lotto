package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.LottoConstant.PRICE_OF_LOTTO;

public class LottoTickets {

	private final List<LottoTicket> lottoTickets = new ArrayList<>();

	public void purchase(List<LottoNumbers> lottoNumbers) {
		for (LottoNumbers eachLottoNumbers : lottoNumbers) {
			lottoTickets.add(new LottoTicket(eachLottoNumbers));
		}
	}

	public Result generateResult(AnswerLotto answerLotto) {
		Result results = new Result();
		for (LottoTicket lotto : this.lottoTickets) {
			results.addResult(lotto.calculate(answerLotto));
		}

		results.setProfitRate(this.lottoTickets.size() * PRICE_OF_LOTTO);

		return results;
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(this.lottoTickets);
	}

}
