package domain;

import util.LottoNumbersGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.ErrorConstant.START_ERROR;
import static constant.LottoConstant.PRICE_OF_LOTTO;

public class Lottos {

	private static final String DIVIDE_BY_THOUSAND = START_ERROR + "1000원으로 나누어 떨어지는 금액을 입력해주세요.";

	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public static Lottos of(int money, LottoNumbersGenerator lottoNumbersGenerator) {
		validateMoney(money);
		int count = money / PRICE_OF_LOTTO;

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

	private static void validateMoney(int money) {
		if (money % PRICE_OF_LOTTO != 0 || money < PRICE_OF_LOTTO) {
			throw new IllegalArgumentException(DIVIDE_BY_THOUSAND);
		}
	}
}
