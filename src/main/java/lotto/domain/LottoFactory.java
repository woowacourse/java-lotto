package lotto.domain;

import java.util.*;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottoFactory {
	private static Map<LottoType, LottoCreator> creators = new HashMap<>();
	private static List<LottoNumber> allLottoNumbers = Arrays.asList(LottoNumber.values());

	static {
		creators.put(LottoType.PAID_LOTTO, new PaidLottoCreator());
		creators.put(LottoType.WINNING_LOTTO, new WinningLottoCreator());
	}

	public static Lotto getLottoAuto(LottoType lottoType) {
		Objects.requireNonNull(lottoType);
		LottoCreator lottoCreator = creators.get(lottoType);

		List<LottoNumber> lottoNumbers;
		Collections.shuffle(allLottoNumbers);
		lottoNumbers = allLottoNumbers.subList(0, 6);

		return lottoCreator.create(lottoNumbers);
	}

	// 로또 번호 직접 입력시 사용하는 메서드
	public static Lotto getWinningLotto(LottoType lottoType, List<LottoNumber> winningLottoNumbers) {
		Objects.requireNonNull(lottoType);
		LottoCreator lottoCreator = creators.get(lottoType);
		return lottoCreator.create(winningLottoNumbers);
	}
}

class PaidLottoCreator implements LottoCreator {
	@Override
	public Lotto create(List<LottoNumber> lottoNumbers) {
		return new PaidLotto(lottoNumbers);
	}
}

class WinningLottoCreator implements LottoCreator {
	@Override
	public Lotto create(List<LottoNumber> lottoNumbers) {
		return new WinningLotto(lottoNumbers);
	}
}
