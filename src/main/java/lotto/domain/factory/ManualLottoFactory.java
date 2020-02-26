package lotto.domain.factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.exception.NotIntegerException;

public class ManualLottoFactory implements LottoMakeAble {
	private static final String COMMA = ",";

	private final String[] inputNumbers;

	public ManualLottoFactory(String input) {
		this.inputNumbers = input.split(COMMA);
	}

	@Override
	public Lotto create() {
		try {
			List<LottoNo> lottoNos = Arrays.stream(this.inputNumbers)
				.map(String::trim)
				.map(LottoNo::new)
				.collect(Collectors.toList());
			Collections.sort(lottoNos);
			return new Lotto(lottoNos);
		} catch (NumberFormatException e) {
			throw new NotIntegerException();
		}
	}
}
