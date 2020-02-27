package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 로또 타입에 맞는 로또를 생성해주는 팩토리, 예외처리 포함
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottoFactory {
	private static Map<LottoType, LottoCreator> creators = new HashMap<>();
	private static List<LottoNumber> allLottoNumbers = LottoNumber.getLottoNumberCache();

	static {
		creators.put(LottoType.MANUAL_LOTTO, new ManualLottoCreator());
		creators.put(LottoType.AUTO_LOTTO, new AutoLottoCreator());
		creators.put(LottoType.WINNING_LOTTO, new ManualLottoCreator());
	}


	public static Lotto createAutoLotto(final LottoType lottoType) {
		Objects.requireNonNull(lottoType);
		LottoCreator lottoCreator = creators.get(lottoType);
		Collections.shuffle(allLottoNumbers);

		List<LottoNumber> lottoNumbers = allLottoNumbers.stream()
				.limit(6)
				.sorted()
				.collect(Collectors.toList());
		return lottoCreator.create(lottoNumbers);
	}

	public static Lotto createManualLotto(final LottoType lottoType, final List<LottoNumber> inputLottoNumbers) {
		Objects.requireNonNull(lottoType);
		LottoCreator lottoCreator = creators.get(lottoType);
		return lottoCreator.create(inputLottoNumbers);
	}
}

class ManualLottoCreator implements LottoCreator {
	@Override
	public Lotto create(List<LottoNumber> lottoNumbers) {
		return new Lotto(lottoNumbers);
	}
}

class AutoLottoCreator implements LottoCreator {
	@Override
	public Lotto create(final List<LottoNumber> lottoNumbers) {
		return new Lotto(lottoNumbers);
	}
}
//
//class WinningLottoCreator implements LottoCreator {
//	@Override
//	public Lotto create(final List<LottoNumber> lottoNumbers) {
//		return new WinningLotto(lottoNumbers);
//	}
//}
