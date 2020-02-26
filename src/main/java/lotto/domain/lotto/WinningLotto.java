package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;

import java.util.List;

/**
 * 로또를 상속받은 우승로또, 사용자로부터 입력을 받은대로 생성
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class WinningLotto extends Lotto {
	public WinningLotto(final List<LottoNumber> inputLottoNumbers) {
		super(inputLottoNumbers);
	}
}
