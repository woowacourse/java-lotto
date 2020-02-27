package lotto.domain.ticket;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoTicket {
	private static final String BALL_COUNT_EXCEPTION_MESSAGE = "로또 볼의 갯수가 적절하지 않습니다.";
	private static final String NULL_LOTTO_EXCEPTION_MESSAGE = "null 데이터는 허용되지 않습니다.";
	static final int BALL_COUNT = 6;

	private final Set<LottoBall> lottoBalls;

	public LottoTicket(Set<LottoBall> lottoBalls) {
		validate(lottoBalls);
		this.lottoBalls = Collections.unmodifiableSet(new TreeSet<>(lottoBalls));
	}

	private void validate(Set<LottoBall> lottoBalls) {
		validateNull(lottoBalls);
		validateBallCount(lottoBalls);
	}

	private void validateNull(Set<LottoBall> lottoBalls) {
		if (Objects.isNull(lottoBalls)) {
			throw new NullPointerException(NULL_LOTTO_EXCEPTION_MESSAGE);
		}
	}

	private void validateBallCount(Set<LottoBall> lottoBalls) {
		if (lottoBalls.size() != BALL_COUNT) {
			throw new IllegalArgumentException(BALL_COUNT_EXCEPTION_MESSAGE);
		}
	}

	public static LottoTicket of(String... lottoNumbers) {
		return Arrays.stream(lottoNumbers)
			.map(String::trim)
			.mapToInt(Integer::parseInt)
			.mapToObj(LottoBall::valueOf)
			.collect(Collectors.collectingAndThen(Collectors.toSet(), LottoTicket::new));
	}

	public static LottoTicket of(int... lottoNumbers) {
		return Arrays.stream(lottoNumbers)
			.mapToObj(LottoBall::valueOf)
			.collect(Collectors.collectingAndThen(Collectors.toSet(), LottoTicket::new));
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
