package lotto.domain;

import java.util.List;

/**
 * 클래스 이름 : LottoCreator.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public interface LottoCreator {
	Lotto create(List<Integer> lottoNumbers);
}
