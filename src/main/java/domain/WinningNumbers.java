package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.LottoInputException;

public class WinningNumbers {
	private final Lotto winningLottoTicket;
	private final LottoNumber bonusNumber;

	public WinningNumbers(String sixNumbers, String bonusNumber) {
		this.winningLottoTicket = new Lotto(sixNumbers);
		this.bonusNumber = LottoNumber.get(Integer.parseInt(bonusNumber));
		duplicationValidate(winningLottoTicket, bonusNumber);
	}

	private void duplicationValidate(Lotto winningLottoTicket, String bonusNumber) {
		if (isBonusNumberContain(winningLottoTicket, bonusNumber)) {
			throw new LottoInputException("당첨번호와 보너스번호가 중복됩니다.");
		}
	}

	private boolean isBonusNumberContain(Lotto winningLottoTicket, String bonusNumber) {
		return winningLottoTicket
			.getNumbers()
			.contains(bonusNumber);
	}

	public Map<Rank, Integer> compareLottos(List<Lotto> lottos) {
		Map<Rank, Integer> ranks = new HashMap<>();
		for (Lotto lotto : lottos) {
			compareLotto(ranks, lotto);
		}
		return ranks;
	}

	private void compareLotto(Map<Rank, Integer> ranks, Lotto lotto) {
		Rank rank = lotto.compare(winningLottoTicket, bonusNumber);
		if (rank != null) {
			ranks.put(rank, ranks.getOrDefault(rank, 0));
		}
	}
}
