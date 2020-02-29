package lotto.domain;

import java.util.stream.Collectors;

import lotto.util.StringUtils;

public class LottoFactory {
	public static Lotto create(String lottoInput) {
		return StringUtils.splitByComma(lottoInput)
				.stream()
				.map(String::trim)
				.map(LottoNumber::of)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));
	}
}
