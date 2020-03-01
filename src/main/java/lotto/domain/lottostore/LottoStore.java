package lotto.domain.lottostore;

import java.util.List;

import lotto.domain.lotto.InvalidLottosException;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.LottosFactory;
import lotto.domain.lottocount.LottoCount;

/**
 * 자동과 수동 로또를 장수만큼 Lottos로 만들어주는 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/28
 */
public class LottoStore {
	private static final int MINIMUM_COUNT = 0;

	public Lottos buy(LottoCount manualLottoCount, LottoCount autoLottoCount, List<String> inputManualLottoNumbers) {
		validate(manualLottoCount, inputManualLottoNumbers);
		if (isManualAndAuto(autoLottoCount, manualLottoCount)) {
			Lottos manualLottos = LottosFactory.createManualLottos(inputManualLottoNumbers);
			Lottos autoLottos = LottosFactory.createAutoLottos(autoLottoCount.getLottoCount());
			return manualLottos.add(autoLottos);
		}
		if (isOnlyAuto(manualLottoCount)) {
			return LottosFactory.createAutoLottos(autoLottoCount.getLottoCount());
		}
		return LottosFactory.createManualLottos(inputManualLottoNumbers);
	}

	private void validate(LottoCount manualLottoCount, List<String> inputManualLottoNumbers) {
		if (manualLottoCount.getLottoCount() != inputManualLottoNumbers.size()) {
			throw new InvalidLottosException("구매하려는 수동 장수와 입력 수동 장수가 맞지 않습니다.");
		}
	}

	private boolean isManualAndAuto(LottoCount autoLottoCount, LottoCount manualLottoCount) {
		return autoLottoCount.getLottoCount() != MINIMUM_COUNT && manualLottoCount.getLottoCount() != MINIMUM_COUNT;
	}

	private boolean isOnlyAuto(LottoCount manualLottoCount) {
		return manualLottoCount.getLottoCount() == MINIMUM_COUNT;
	}
}
