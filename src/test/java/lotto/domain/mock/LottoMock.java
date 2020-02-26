package lotto.domain.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;

public class LottoMock {
	public static Set<LottoNumber> getLottoNumberSet(int... nums) {
		Set<LottoNumber> lottoNumbers = new HashSet<>();
		for (int n : nums) {
			lottoNumbers.add(LottoNumber.valueOf(n));
		}
		return lottoNumbers;
	}

	public static Lotto getLottoMock() {
		Set<LottoNumber> lottoNumbers = getLottoNumberSet(1, 2, 3, 4, 5, 6);
		return new Lotto(lottoNumbers);
	}

	public static Lottos getLottosMock() {
		Lotto lotto = getLottoMock();

		List<Lotto> lottos = new ArrayList<>();
		lottos.add(lotto);

		return new Lottos(lottos);
	}
}
