package lotto.domain;

import java.util.List;
import java.util.Objects;

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

	public int getHowManyContain(PaidLotto paidLotto) {
		Objects.requireNonNull(paidLotto, "매개변수가 null 입니다.");
		return (int) paidLotto.getLottoNumbers()
				.stream()
				.filter(this::isContain)
				.count();
	}
}
