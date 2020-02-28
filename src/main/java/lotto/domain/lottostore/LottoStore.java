package lotto.domain.lottostore;

import java.util.List;

import lotto.domain.lotto.InvalidLottosException;
import lotto.domain.lotto.LottoCount;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.LottosFactory;

/**
 * 자동과 수동 로또를 장수만큼 Lottos로 만들어주는 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/28
 */
public class LottoStore {
	private static final String COUNT_MISMATCH_EXCEPTION_MESSAGE = "구매하려는 수동 장수와 입력 수동 장수가 맞지 않습니다.";
	private static final int MINIMUM_COUNT = 0;

	public Lottos buy(LottoCount lottoCount, List<String> inputManualLottoNumbers) {
		validate(lottoCount, inputManualLottoNumbers);
		if (isManualAndAuto(lottoCount)) {
			Lottos manualLottos = LottosFactory.createManualLottos(inputManualLottoNumbers);
			Lottos autoLottos = LottosFactory.createAutoLottos(lottoCount.getAutoLottoCount());
			return manualLottos.add(autoLottos);
		}
		if (isOnlyManual(lottoCount)) {
			return LottosFactory.createManualLottos(inputManualLottoNumbers);
		}
		return LottosFactory.createAutoLottos(lottoCount.getAutoLottoCount());
	}

	private void validate(LottoCount lottoCount, List<String> inputManualLottoNumbers) {
		if (lottoCount.getManualLottoCount() != inputManualLottoNumbers.size()) {
			throw new InvalidLottosException(COUNT_MISMATCH_EXCEPTION_MESSAGE);
		}
	}

	private boolean isOnlyManual(LottoCount lottoCount) {
		return lottoCount.getAutoLottoCount() == MINIMUM_COUNT;
	}

	private boolean isManualAndAuto(LottoCount lottoCount) {
		return lottoCount.getManualLottoCount() != MINIMUM_COUNT && lottoCount.getAutoLottoCount() != MINIMUM_COUNT;
	}
}
