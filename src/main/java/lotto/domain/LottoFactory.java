package lotto.domain;

import java.util.stream.Collectors;

import lotto.util.StringUtils;

public class LottoFactory {
	public static Lotto createLotto(String lottoInput) {
		return StringUtils.splitByComma(lottoInput)
				.stream()
				.map(String::trim)
				.map(LottoNumber::of)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));
	}

	public static WinningLotto createWinningLotto(String lottoInput, String bonusInput) {
		Lotto lotto = createLotto(lottoInput);
		LottoNumber bonus = LottoNumber.of(bonusInput);
		return new WinningLotto(lotto, bonus);
	}
}
