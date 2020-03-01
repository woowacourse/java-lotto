package lotto.domain.lottostrategy;

import java.util.*;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
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
	private static List<LottoNumber> allLottoNumbers = LottoNumber.getLottoNumberCache();

	public static Lotto createAutoLotto() {
		Collections.shuffle(allLottoNumbers);

		List<LottoNumber> lottoNumbers = allLottoNumbers.stream()
				.limit(6)
				.sorted()
				.collect(Collectors.toList());
		return new Lotto(lottoNumbers);
	}

	public static Lotto createManualLotto(final List<LottoNumber> inputLottoNumbers) {
		return new Lotto(inputLottoNumbers);
	}
}
