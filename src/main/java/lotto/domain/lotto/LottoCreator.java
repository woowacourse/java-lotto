package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;

import java.util.List;

/**
 * LottoCreator 인터페이스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public interface LottoCreator {
	Lotto create(List<LottoNumber> lottoNumbers);
}
