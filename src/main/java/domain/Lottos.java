package domain;

import util.LottoNumbersGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.LottoConstant.PRICE_OF_LOTTO;

public class Lottos {

	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public static Lottos of(Money money, LottoNumbersGenerator lottoNumbersGenerator) {
		int count = money.calculateCount();

		List<Lotto> lottos = new ArrayList<>();
		while (count-- > 0) {
			lottos.add(new Lotto(lottoNumbersGenerator));
		}

		return new Lottos(lottos);
	}

	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(this.lottos);
	}

	public int getLottosSize() {
		return this.lottos.size();
	}

	public Result generateResult(AnswerLotto answerLotto) {
		Result results = new Result();
		for (Lotto lotto : this.lottos) {
			results.addResult(lotto.calculate(answerLotto));
		}

		results.setProfitRate(this.lottos.size() * PRICE_OF_LOTTO);

		return results;
	}
}
