package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class LottoTicket {
	private static final String DELIMITER = ", ";
	private static final String START_BRACKET = "[";
	private static final String END_BRACKET = "]";

	private final List<LottoNumber> lottoNumbers;

	public LottoTicket(final List<LottoNumber> lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	public Stream<LottoNumber> stream() {
		return lottoNumbers.stream();
	}

	@Override
	public String toString() {
		return START_BRACKET +
			lottoNumbers.stream()
			.map(LottoNumber::toString)
			.collect(joining(DELIMITER)) +
			END_BRACKET;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoTicket that = (LottoTicket)o;
		return Objects.equals(lottoNumbers, that.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumbers);
	}
}
