package lotto.domain;

import java.util.stream.Collectors;

import lotto.util.StringUtils;

public class LottoFactory {
	public static Lotto create(String input) {
		return StringUtils.splitByComma(input)
				.stream()
				.map(LottoNumber::of)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));
	}
}
