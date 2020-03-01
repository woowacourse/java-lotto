package lotto.domain.lotto;

import java.util.*;
import java.util.stream.Collectors;

import lotto.domain.lottonumber.LottoNumber;

/**
 * 로또 타입에 맞는 로또를 생성해주는 팩토리, 예외처리 포함
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottoFactory {
	private List<LottoNumber> allLottoNumbers;

	public LottoFactory() {
		allLottoNumbers = LottoNumber.getLottoNumberCache();
	}

	public Lotto createAutoLotto() {
		Collections.shuffle(allLottoNumbers);

		List<LottoNumber> lottoNumbers = allLottoNumbers.stream()
				.limit(6)
				.sorted()
				.collect(Collectors.toList());
		return new Lotto(lottoNumbers);
	}

	public Lotto createManualLotto(final List<LottoNumber> inputLottoNumbers) {
		return new Lotto(inputLottoNumbers);
	}
}
