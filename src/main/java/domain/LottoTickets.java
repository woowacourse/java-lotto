package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
	private static final String ERROR_NOT_DIVISIBLE_BY_UNIT_PRICE = "[ERROR] 금액은 1000원 단위로 나누어 떨어져야 합니다.";
	private static final String ERROR_NOT_POSITIVE = "[ERROR] 금액은 0원일 수 없습니다.";
	private static final String ERROR_CANNOT_ADD = "[ERROR] 전체 로또 갯수보다 더 많은 로또를 추가할 수 없습니다.";
	private static final int UNIT_PRICE = 1000;
	private final List<Lotto> lottoTickets = new ArrayList<>();
	private final int capacity;

	public LottoTickets(int price) {
		validateDivisibleByThousand(price);
		validatePositive(price);
		this.capacity = price / UNIT_PRICE;
	}

	public int getLottoTicketsSize() {
		return this.lottoTickets.size();
	}

	public int calculateRemainLottoCount() {
		return this.capacity - this.lottoTickets.size();
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
		validateLessThanCapacity();
		this.lottoTickets.add(lotto);
	}

	private void validateDivisibleByThousand(int price) {
		if (price % UNIT_PRICE != 0) {
			throw new IllegalArgumentException(ERROR_NOT_DIVISIBLE_BY_UNIT_PRICE);
		}
	}

	private void validatePositive(int price) {
		if (price == 0) {
			throw new IllegalArgumentException(ERROR_NOT_POSITIVE);
		}
	}

	private void validateLessThanCapacity() {
		if (this.lottoTickets.size() + 1 > this.capacity) {
			throw new IllegalArgumentException(ERROR_CANNOT_ADD);
		}
	}
}
