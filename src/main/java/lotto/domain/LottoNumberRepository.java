package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumberRepository {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

	static {
		IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
			.mapToObj(Integer::toString)
			.map(LottoNumber::new)
			.forEach(lottoNumbers::add);
	}

	public static List<LottoNumber> shuffledLottoNumbers() {
		Collections.shuffle(lottoNumbers);
		return Collections.unmodifiableList(lottoNumbers);
	}
}
