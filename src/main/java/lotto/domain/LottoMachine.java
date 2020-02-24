package lotto.domain;

import lotto.dto.LottoCountDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoMachine {
	private static final int MAX_LOTTO_NUMBER = 45;
	private static final int MIN_LOTTO_NUMBER = 1;

	private final List<Integer> lottoBalls;

	public LottoMachine() {
		lottoBalls = new ArrayList<>();
		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			lottoBalls.add(i);
		}
	}

	public List<Lotto> makeRandomLottos(LottoCountDto lottoCountDto) {
		List<Lotto> lottos = new ArrayList<>();
		int lottoCount = lottoCountDto.getLottoCount();

		for (int i = 0; i < lottoCount; i++) {
			lottos.add(new Lotto(pickRandomBalls()));
		}
		return lottos;
	}

	private List<Integer> pickRandomBalls() {
		Collections.shuffle(lottoBalls);
		return lottoBalls.stream()
				.limit(6)
				.sorted()
				.collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoMachine that = (LottoMachine) o;
		return Objects.equals(lottoBalls, that.lottoBalls);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoBalls);
	}
}
