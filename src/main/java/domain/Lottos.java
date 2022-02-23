package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

	private final List<Lotto> lottos;

	public Lottos(List<Lotto> nowLottos) {
		this.lottos = nowLottos;
	}

	public static Lottos of(int price, LottoNumbersGenerator lottoNumbersGenerator) {
		List<Lotto> nowLottos = new ArrayList<>();
		int count = price / 1000;
		while (count-- > 0) {
			nowLottos.add(new Lotto(lottoNumbersGenerator));
		}
		return new Lottos(nowLottos);
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}

	public int getLottosSize() {
		return this.lottos.size();
	}

	public float generateProfitRatio(AnswerLotto answerLotto, int price) {
		int totalPrize = 0;
		for (Lotto lotto : this.lottos) {
			totalPrize += lotto.calculate(answerLotto).getPrice();
		}
		return (float) totalPrize / price;
	}
}
