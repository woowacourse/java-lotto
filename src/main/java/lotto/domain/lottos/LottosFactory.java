package lotto.domain.lottos;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lotto.LottoFactory;
import lotto.util.StringUtils;

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

	public static Lottos createManualLottos(final List<String> inputManualLottos) {
		LottoFactory lottoFactory = new LottoFactory();
		List<Lotto> manualLottos = new ArrayList<>();

		for (String inputManualLotto : inputManualLottos) {
			List<LottoNumber> manualLotto = StringUtils.splitIntoLottoNumbers(inputManualLotto);
			manualLottos.add(lottoFactory.createManualLotto(manualLotto));
		}

		return new Lottos(manualLottos);
	}

	public static Lottos createAutoLottos(final int amountOfLottos) {
		LottoFactory lottoFactory = new LottoFactory();
		List<Lotto> autoLottos = new ArrayList<>();
		for (int i = 0; i < amountOfLottos; i++) {
			autoLottos.add(lottoFactory.createAutoLotto());
		}
		return new Lottos(autoLottos);
	}
}
