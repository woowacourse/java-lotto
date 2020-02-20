package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {
	private static final String DELIMITER = ", ";
	private static final int SIX = 6;
	private final Lotto winningLottoTicket;
	private final LottoNumber bonusNumber;

	public WinningNumbers(String sixNumbers, String bonusNumber) {
		this.winningLottoTicket = new Lotto(sixNumbers);
		this.bonusNumber = new LottoNumber(bonusNumber);
		duplicationValidate(winningLottoTicket, bonusNumber);
	}

	private void duplicationValidate(Lotto winningLottoTicket, String bonusNumber) {
		if (bonusNumberContain(winningLottoTicket, bonusNumber)) {
			throw new IllegalArgumentException("당첨번호와 보너스번호가 중복됩니다.");
		}
	}

	private boolean bonusNumberContain(Lotto winningLottoTicket, String bonusNumber) {
		return winningLottoTicket
			.getNumbers()
			.contains(bonusNumber);
	}

	public List<Rank> compareLottos(List<Lotto> lottos) {
		List<Rank> ranks = new ArrayList<>();

		for (Lotto lotto : lottos) {
			compareLotto(ranks, lotto);
		}
		return ranks;
	}

	private void compareLotto(List<Rank> ranks, Lotto lotto) {
		Rank rank = lotto.compare(winningLottoTicket, bonusNumber);
		if (rank != null) {
			ranks.add(rank);
		}
	}
}
