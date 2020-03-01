package lotto.domain.lottos;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lottocount.LottoCount;

import java.util.ArrayList;
import java.util.List;

/**
 * 입력받은 수만큼 자동으로 로또를 만들어서 Lottos를 리턴하는 객체
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/01
 */
public class AutoLottosMachine implements LottosGeneratable {
	private LottoCount autoLottoCount;

	public AutoLottosMachine(final LottoCount inputLottoCount) {
		this.autoLottoCount = inputLottoCount;
	}

	@Override
	public Lottos create() {
		LottoFactory lottoFactory = new LottoFactory();
		List<Lotto> autoLottos = new ArrayList<>();
		for (int i = 0; i < this.autoLottoCount.getLottoCount(); i++) {
			autoLottos.add(lottoFactory.createAutoLotto());
		}
		return new Lottos(autoLottos);
	}
}
