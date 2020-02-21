package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.util.StringUtil;

public class LottoTicketFactory {
	public static LottoTicket of(String rawWinningLotto) {
		List<Ball> collect = Arrays.stream(StringUtil.splitRawLottoNumbers(rawWinningLotto))
			.mapToInt(Integer::parseInt)
			.mapToObj(Ball::of)
			.collect(Collectors.toList());
		return new LottoTicket(collect);
	}
}
