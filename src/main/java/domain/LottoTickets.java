package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
	private final List<Lotto> lottoTickets = new ArrayList<>();
	private final LottoCounter lottoCounter;

	public LottoTickets(LottoCounter lottoCounter) {
		this.lottoCounter = lottoCounter;
	}

	public int getManualLottoTicketsSize() {
		return this.lottoCounter.getManualSize();
	}

	public int getAutoLottoTicketsSize() {
		return this.lottoCounter.getTotalSize() - this.lottoCounter.getManualSize();
	}

	public List<Lotto> getLottoTickets() {
		return new ArrayList<>(this.lottoTickets);
	}

	public List<WinningStatus> calculateAllLottoResult(AnswerLotto answerLotto) {
		List<WinningStatus> allLottoResults = new ArrayList<>();
		for (Lotto lotto : this.lottoTickets) {
			allLottoResults.add(WinningStatus.of(lotto.calculateInAnswerNumbers(answerLotto),
				lotto.isHitBonusNumber(answerLotto)));
		}
		return allLottoResults;
	}

	public void add(Lotto lotto) {
		this.lottoTickets.add(lotto);
	}
}
