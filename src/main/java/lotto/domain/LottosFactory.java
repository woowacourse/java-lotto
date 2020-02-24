package lotto.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * LottosFactory 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/21
 */
public class LottosFactory {
	private LottosFactory() {}

	public static Lottos createLottosAuto(int amountOfLottos) {
		List<Lotto> paidLotto = new ArrayList<>();

		for (int i = 0; i < amountOfLottos; i++) {
			paidLotto.add(LottoFactory.createLottoAuto());
		}
		return new Lottos(paidLotto);
	}
}
