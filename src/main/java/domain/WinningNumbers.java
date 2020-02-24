package domain;

import exception.LottoInputException;

public class WinningNumbers {
	private final Lotto winningLottoTicket;
	private final LottoNumber bonusNumber;

	public WinningNumbers(String sixNumbers, String bonusNumber) {
		this.winningLottoTicket = new Lotto(sixNumbers);
		this.bonusNumber = LottoNumber.get(Integer.parseInt(bonusNumber));
		duplicationValidate(this.winningLottoTicket, this.bonusNumber);
	}

	private void duplicationValidate(Lotto winningLottoTicket, LottoNumber bonusNumber) {
		if (winningLottoTicket.isContains(bonusNumber)) {
			throw new LottoInputException("당첨번호와 보너스번호가 중복됩니다.");
		}
	}
}
