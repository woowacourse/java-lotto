package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.List;

public class LottoTicket {
	public static final String DELIMITER = ", ";
	public static final String START_BRACKET = "[";
	public static final String END_BRACKET = "]";

	private final List<LottoNumber> lottoNumbers;

	public LottoTicket(List<LottoNumber> lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	@Override
	public String toString() {
		return START_BRACKET +
			lottoNumbers.stream()
			.map(LottoNumber::toString)
			.collect(joining(DELIMITER)) +
			END_BRACKET;
	}
}
