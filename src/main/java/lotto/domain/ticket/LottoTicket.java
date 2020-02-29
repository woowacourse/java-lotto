package lotto.domain.ticket;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class LottoTicket {
	private static final String BALL_COUNT_EXCEPTION_MESSAGE = "로또 볼의 갯수가 적절하지 않습니다.";
	static final int BALL_COUNT = 6;

	private final Set<LottoBall> lottoBalls;

	LottoTicket(Set<LottoBall> lottoBalls) {
		validateBallCount(lottoBalls);
		this.lottoBalls = Collections.unmodifiableSet(new TreeSet<>(Objects.requireNonNull(lottoBalls)));
	}

	private void validateBallCount(Set<LottoBall> lottoBalls) {
		if (lottoBalls.size() != BALL_COUNT) {
			throw new IllegalArgumentException(BALL_COUNT_EXCEPTION_MESSAGE);
		}
	}

	public static LottoTicket of(List<Integer> lottoNumbers) {
		return lottoNumbers.stream()
			.map(LottoBall::valueOf)
			.collect(collectingAndThen(toSet(), LottoTicket::new));
	}

	static LottoTicket of(int... lottoNumbers) {
		return Arrays.stream(lottoNumbers)
			.mapToObj(LottoBall::valueOf)
			.collect(collectingAndThen(toSet(), LottoTicket::new));
	}

	public boolean contains(LottoBall lottoBall) {
		return lottoBalls.contains(lottoBall);
	}

	public int countMatchingBall(LottoTicket lottoTicket) {
		Set<LottoBall> sameBalls = new HashSet<>(lottoBalls);
		sameBalls.retainAll(lottoTicket.lottoBalls);
		return sameBalls.size();
	}

	public Set<LottoBall> getLottoBalls() {
		return lottoBalls;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		LottoTicket that = (LottoTicket)object;
		return Objects.equals(this.lottoBalls, that.lottoBalls);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoBalls);
	}
}
