package lotto.domain;

import java.util.Objects;
import java.util.stream.Collectors;

public class WinningLotto {
	private static final String DUPLICATE_EXCEPTION_MESSAGE = "당첨 번호와 보너스 볼이 중복되었습니다.";
	private static final String NULL_WINNING_LOTTO_EXCEPTION_MESSAGE = "객체에 NULL 이 들어갈 수 없습니다.";

	private final LottoTicket winningLottoTicket;
	private final LottoBall bonusLottoBall;

	public WinningLotto(LottoTicket winningLottoTicket, LottoBall bonusLottoBall) {
		validate(winningLottoTicket, bonusLottoBall);
		this.winningLottoTicket = winningLottoTicket;
		this.bonusLottoBall = bonusLottoBall;
	}

	private void validate(LottoTicket winningLottoTicket, LottoBall bonusLottoBall) {
		validateNull(winningLottoTicket, bonusLottoBall);
		validateDuplication(winningLottoTicket, bonusLottoBall);
	}

	private void validateNull(LottoTicket winningLottoTicket, LottoBall bonusLottoBall) {
		if (Objects.isNull(winningLottoTicket) || Objects.isNull(bonusLottoBall)) {
			throw new NullPointerException(NULL_WINNING_LOTTO_EXCEPTION_MESSAGE);
		}
	}

	private void validateDuplication(LottoTicket winningLottoTicket, LottoBall bonusLottoBall) {
		if (winningLottoTicket.contains(bonusLottoBall)) {
			throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
		}
	}

	public WinningResult calculateResult(LottoTickets lottoTickets) {
		return lottoTickets.getLottoTickets().stream()
			.map(this::calculateRank)
			.collect(Collectors.collectingAndThen(Collectors.toList(), WinningResult::new));
	}

	LottoRank calculateRank(LottoTicket lottoTicket) {
		int matchCount = lottoTicket.countMatchingBall(winningLottoTicket);
		boolean hasBonusBall = lottoTicket.contains(bonusLottoBall);
		return LottoRank.findRank(matchCount, hasBonusBall);
	}
}
