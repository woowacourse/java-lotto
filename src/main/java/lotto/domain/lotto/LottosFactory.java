package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;
import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Lottos를 만드는 팩토리
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/21
 */
public class LottosFactory {
	private LottosFactory() {
	}

	public static Lottos createManualLottos(List<String> inputManualLottos) {
		List<Lotto> manualLottos = new ArrayList<>();

		for (String inputManualLotto : inputManualLottos) {
			List<LottoNumber> manualLotto = StringUtils.splitIntoLottoNumbers(inputManualLotto);
			manualLottos.add(LottoFactory.createManualLotto(LottoType.MANUAL_LOTTO, manualLotto));
		}

		return new Lottos(manualLottos);
	}

	public static Lottos createAutoLottos(int amountOfLottos) {
		List<Lotto> paidLottos = new ArrayList<>();
		// TODO: 2020/02/26 우선 페이드로또 없에고 위닝도 없에고 해야할듯....
		for (int i = 0; i < amountOfLottos; i++) {
			paidLottos.add(LottoFactory.createAutoLotto(LottoType.AUTO_LOTTO));
		}
		return new Lottos(paidLottos);
	}
}
