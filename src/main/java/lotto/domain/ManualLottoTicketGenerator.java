package lotto.domain;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoTicketGenerator implements LottoTicketGenerator {
	@Override
	public LottoTicket create() {
		return new LottoTicket(createRandomBalls());
	}

	private Set<LottoBall> createRandomBalls() {
		return Arrays.stream(new Scanner(System.in).nextLine().split(", "))
			.mapToInt(Integer::parseInt)
			.mapToObj(LottoBall::valueOf)
			.collect(Collectors.toSet());
	}
}
