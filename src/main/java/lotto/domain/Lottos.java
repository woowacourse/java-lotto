package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lotto.util.Count;

/**
 * 클래스 이름 : Lottos.java
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class Lottos {
	private final List<Lotto> lottos;

	private Lottos(final List<Lotto> inputLottos) {
		Objects.requireNonNull(inputLottos, "입력이 null일 수 없습니다.");
		validateEmptyInput(inputLottos);
		this.lottos = Collections.unmodifiableList(inputLottos);
	}

	private void validateEmptyInput(final List<Lotto> inputLottos) {
		if (inputLottos.isEmpty()) {
			throw new IllegalArgumentException("로또는 한 장 이상 사야합니다.");
		}
	}

	public static Lottos createLottos(LottoCount lottoCount, List<List<Integer>> lottosInput) {
		List<Lotto> paidLotto = new ArrayList<>();
		paidLotto.addAll(makeLottoListManual(lottosInput));
		paidLotto.addAll(makeLottoListAuto(lottoCount.getAutoLottoCount()));
		return new Lottos(paidLotto);
	}

	public static Lottos createLottosAuto(LottoMoney money) {
		return createLottos(new LottoCount(money), new ArrayList<>());
	}

	private static List<Lotto> makeLottoListManual(List<List<Integer>> lottosInput) {
		List<Lotto> lottos = new ArrayList<>();

		for (List<Integer> lotto : lottosInput) {
			lottos.add(Lotto.createLottoManual(lotto));
		}

		return lottos;
	}

	private static List<Lotto> makeLottoListAuto(Count amountOfAutoLottos) {
		List<Lotto> lottos = new ArrayList<>();

		for (long i = 0; i < amountOfAutoLottos.getCount(); i++) {
			lottos.add(Lotto.createLottoAuto());
		}

		return lottos;
	}

	public int getAmountOfLottos() {
		return this.lottos.size();
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}
}
