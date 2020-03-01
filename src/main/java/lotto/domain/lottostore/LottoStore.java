package lotto.domain.lottostore;

import java.util.List;

import lotto.domain.lottos.InvalidLottosException;
import lotto.domain.lottos.Lottos;
import lotto.domain.lottos.LottosFactory;
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
	public Lottos buy(LottoCount manualLottoCount, LottoCount autoLottoCount, List<String> inputManualLottoNumbers) {
		validate(manualLottoCount, inputManualLottoNumbers);
		Lottos manualLottos = LottosFactory.createManualLottos(inputManualLottoNumbers);
		Lottos autoLottos = LottosFactory.createAutoLottos(autoLottoCount.getLottoCount());
		return manualLottos.add(autoLottos);
	}

	private void validate(LottoCount manualLottoCount, List<String> inputManualLottoNumbers) {
		if (manualLottoCount.getLottoCount() != inputManualLottoNumbers.size()) {
			throw new InvalidLottosException("구매하려는 수동 장수와 입력 수동 장수가 맞지 않습니다.");
		}
	}
}
