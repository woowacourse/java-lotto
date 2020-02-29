package lotto.domain;

public class WinningNumbers {
	private static final String NO_DUPLICATED_IN_BONUS_AND_TICKET = "보너스 번호는 일반 당첨번호와 중복될수 없습니다.";

	private final LottoTicket winningTicket;
	private final LottoNumber bonusNumber;

	public WinningNumbers(String winningTicketValues, String bonusNumber) {
		this.winningTicket = LottoTicket.of(winningTicketValues);
		LottoNumber bonus = new LottoNumber(bonusNumber);
		checkValidationOf(winningTicket, bonus);
		this.bonusNumber = bonus;
	}

	private void checkValidationOf(LottoTicket winningTicket, LottoNumber bonus) {
		if (winningTicket.contains(bonus)) {
			throw new IllegalArgumentException(NO_DUPLICATED_IN_BONUS_AND_TICKET);
		}
	}

	public LottoTicket getWinningTicket() {
		return winningTicket;
	}

	public boolean isMatchWithBonus(final LottoNumber lottoNumber) {
		return lottoNumber.equals(bonusNumber);
	}
}
