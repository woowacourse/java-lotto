package domain;

import util.LottoNumbersGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.LottoConstant.PRICE_OF_LOTTO;

public class LottoTickets {

	private final List<LottoTicket> lottoTickets;

	private LottoTickets(List<LottoTicket> lottoTickets) {
		this.lottoTickets = lottoTickets;
	}

	public static LottoTickets of(Money money, LottoNumbersGenerator lottoNumbersGenerator) {
		List<LottoTicket> lottoTickets = new ArrayList<>();

		for (LottoNumbers lottoNumbers : lottoNumbersGenerator.generate(money.calculateCount())) {
			lottoTickets.add(new LottoTicket(lottoNumbers));
		}

		return new LottoTickets(lottoTickets);
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(this.lottoTickets);
	}

	public int getLottoTicketsSize() {
		return this.lottoTickets.size();
	}

	public Result generateResult(AnswerLotto answerLotto) {
		Result results = new Result();
		for (LottoTicket lotto : this.lottoTickets) {
			results.addResult(lotto.calculate(answerLotto));
		}

		results.setProfitRate(this.lottoTickets.size() * PRICE_OF_LOTTO);

		return results;
	}
}
