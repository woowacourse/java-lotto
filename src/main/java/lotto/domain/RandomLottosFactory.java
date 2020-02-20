package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 로또 번호 자동 생성 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class RandomLottosFactory implements LottoGeneratable {
	private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>(LottoNumber.values());

	@Override
	public Lottos generate(long totalCount) {
		List<Lotto> lottos = new ArrayList<>();
		for (long count = 0; count < totalCount; count++) {
			lottos.add(generate());
		}
		return new Lottos(lottos);
	}

	private Lotto generate() {
		Collections.shuffle(LOTTO_NUMBERS);
		return new Lotto(LOTTO_NUMBERS.stream()
				.limit(Lotto.SIZE)
				.collect(Collectors.toList()));
	}
}
