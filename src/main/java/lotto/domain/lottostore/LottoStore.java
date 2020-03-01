package lotto.domain.lottostore;

import java.util.List;
import java.util.Objects;

import lotto.domain.lottos.*;
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
	private static final String NULL_INPUT_EXCEPTION_MESSAGE = "입력이 null일 수 없습니다.";

	public Lottos purchase(LottoCount autoLottoCount, List<String> inputManualLottoNumbers) {
		Objects.requireNonNull(autoLottoCount, NULL_INPUT_EXCEPTION_MESSAGE);
		Objects.requireNonNull(inputManualLottoNumbers, NULL_INPUT_EXCEPTION_MESSAGE);
		LottosGeneratable manualLottoMachine = new ManualLottosMachine(inputManualLottoNumbers);
		LottosGeneratable autoLottoMachine = new AutoLottosMachine(autoLottoCount);

		Lottos manualLottos = manualLottoMachine.create();
		Lottos autoLottos = autoLottoMachine.create();
		return manualLottos.concat(autoLottos);
	}
}
