package lotto.domain;

import java.util.List;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class WinningLotto extends Lotto {
	public WinningLotto(List<LottoNumber> inputLottoNumbers) {
		super(inputLottoNumbers);
	}

	public boolean isContain(LottoNumber lottoNumber) {
		return lottoNumbers.stream().anyMatch(value -> value == lottoNumber);
	}
}
