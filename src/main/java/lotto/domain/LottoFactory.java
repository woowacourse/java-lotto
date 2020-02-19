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

	public static Lotto getLottoManual(LottoType lottoType, List<LottoNumber> inputLottoNumbers) {
		Objects.requireNonNull(lottoType);
		LottoCreator lottoCreator = creators.get(lottoType);
		return lottoCreator.create(inputLottoNumbers);
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
