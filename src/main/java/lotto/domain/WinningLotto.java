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
	public WinningLotto(final List<LottoNumber> inputLottoNumbers) {
		super(inputLottoNumbers);
	}

//	public int calculateMatchCount(final PaidLotto paidLotto) { // TODO: 2020/02/21 이 기능이 PaidLotto 에 있어야 함
//		Objects.requireNonNull(paidLotto, "매개변수가 null 입니다.");
//		return (int) paidLotto.getLottoNumbers()
//				.stream()
//				.filter(this::isContain)
//				.count();
//	}
}
