package lotto.domain.factory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;

public class AutomaticLottoFactory implements MakeAbleLotto {
	private static final int LOTTO_FROM_INDEX = 0;
	private static final int LOTTO_TO_INDEX = 6;

	private final List<LottoNo> lottoBox;

	public AutomaticLottoFactory() {
		this.lottoBox = IntStream.range(LottoNo.MIN, LottoNo.MAX)
			.boxed()
			.map(String::valueOf)
			.map(LottoNo::new)
			.collect(Collectors.toList());
	}

	@Override
	public Lotto create() {
		Collections.shuffle(this.lottoBox);
		List<LottoNo> lottoNos = this.lottoBox.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
		Collections.sort(lottoNos);
		return new Lotto(lottoNos);
	}
}
