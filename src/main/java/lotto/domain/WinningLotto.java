package lotto.domain;

import java.util.List;
import java.util.Objects;

/**
 * 클래스 이름 : WinningLotto.java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class WinningLotto extends Lotto {
	public WinningLotto(final List<LottoNumber> inputLottoNumbers) {
		super(inputLottoNumbers);
	}
}
