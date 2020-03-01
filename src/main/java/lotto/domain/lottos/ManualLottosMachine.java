package lotto.domain.lottos;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lottonumber.LottoNumber;
import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 입력된 로또들에 따라 수동으로 구성된 Lotts를 반환하는 객체
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/01
 */
public class ManualLottosMachine implements LottosGeneratable {
	private List<String> inputManualLottos;

	public ManualLottosMachine(final List<String> inputManualLottos) {
		this.inputManualLottos = inputManualLottos;
	}

	@Override
	public Lottos create() {
		LottoFactory lottoFactory = new LottoFactory();
		List<Lotto> manualLottos = new ArrayList<>();

		for (String inputManualLotto : inputManualLottos) {
			List<LottoNumber> manualLotto = StringUtils.splitIntoLottoNumbers(inputManualLotto);
			manualLottos.add(lottoFactory.createManualLotto(manualLotto));
		}

		return new Lottos(manualLottos);
	}
}
