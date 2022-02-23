package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lottos {

	/*private final List<Lotto> lottos;

	public Lottos(List<Lotto> nowLottos) {
		this.lottos = nowLottos;
	}

	public static Lottos of(int price, LottoNumbersGenerator lottoNumbersGenerator) {
		validatePrice(price);
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

	public Map<ResultStatics, Integer> generateEachCount(AnswerLotto answerLotto) {
		Map<ResultStatics, Integer> eachCount = new LinkedHashMap<>();

		for (ResultStatics resultStatic : ResultStatics.values()) {
			eachCount.put(resultStatic, 0);
		}

		for (Lotto lotto : this.lottos) {
			ResultStatics resultStatic = lotto.calculate(answerLotto);
			eachCount.put(resultStatic, eachCount.get(resultStatic) + 1);
		}

		return eachCount;
	}

	public float generateProfitRatio(AnswerLotto answerLotto) {
		int totalPrize = 0;
		int totalPrice = this.lottos.size() * 1000;
		for (Lotto lotto : this.lottos) {
			totalPrize += lotto.calculate(answerLotto).getPrice();
		}
		return (float) totalPrize / totalPrice;
	}

	private static void validatePrice(int price) {
		if (price % 1000 != 0 || price == 0) {
			throw new IllegalArgumentException("[ERROR] 1000원으로 나누어 떨어지는 금액을 입력해주세요.");
		}
	}*/
}
