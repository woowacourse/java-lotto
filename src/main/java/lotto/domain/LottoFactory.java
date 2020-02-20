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
	private static final int FROM_INDEX = 0;
	private static final int TO_INDEX = 6;

	private static Map<LottoType, LottoCreator> creators = new HashMap<>();
	private static List<LottoNumber> allLottoNumbers = Arrays.asList(LottoNumber.values());

	static {
		creators.put(LottoType.PAID_LOTTO, new PaidLottoCreator());
		creators.put(LottoType.WINNING_LOTTO, new WinningLottoCreator());
	}

	public static Lotto createLottoAuto(final LottoType lottoType) {
		Objects.requireNonNull(lottoType);
		LottoCreator lottoCreator = creators.get(lottoType);

		List<LottoNumber> lottoNumbers;
		Collections.shuffle(allLottoNumbers);
		lottoNumbers = allLottoNumbers.subList(FROM_INDEX, TO_INDEX);

		return lottoCreator.create(lottoNumbers);
	}

	public static Lotto createLottoManual(final LottoType lottoType, final List<LottoNumber> inputLottoNumbers) {
		Objects.requireNonNull(lottoType);
		LottoCreator lottoCreator = creators.get(lottoType);
		return lottoCreator.create(inputLottoNumbers);
	}
}

class PaidLottoCreator implements LottoCreator {
	@Override
	public Lotto create(final List<LottoNumber> lottoNumbers) {
		return new PaidLotto(lottoNumbers);
	}
}

class WinningLottoCreator implements LottoCreator {
	@Override
	public Lotto create(final List<LottoNumber> lottoNumbers) {
		return new WinningLotto(lottoNumbers);
	}
}
