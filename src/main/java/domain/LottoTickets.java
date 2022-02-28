package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
	private static final String ERROR_NOT_DIVISIBLE_BY_UNIT_PRICE = "[ERROR] 금액은 1000원 단위로 나누어 떨어져야 합니다.";
	private static final String ERROR_NOT_POSITIVE = "[ERROR] 금액은 0원일 수 없습니다.";
	private static final String ERROR_NOT_VALID_MANUAL_LOTTO_SIZE = "[ERROR] 수동 로또 갯수는 전체 로또 갯수보다 작거나 같아야 합니다.";
	private static final String ERROR_CANNOT_ADD = "[ERROR] 전체 로또 갯수보다 더 많이 로또를 추가할 수 없습니다.";
	private static final int UNIT_PRICE = 1000;
	private final List<Lotto> lottoTickets = new ArrayList<>();
	private final int sizeOfLottoTickets;

	public LottoTickets(int price, int manualLottoSize) {
		validateDivisibleByThousand(price);
		validatePositive(price);
		validateManualLottoSize(price, manualLottoSize);
		this.sizeOfLottoTickets = price / UNIT_PRICE;
	}

	public int getLottoTicketsSize() {
		return this.lottoTickets.size();
	}

	public int getLottoTicketsCapacity() {
		return this.sizeOfLottoTickets;
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
		validateFullSize();
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

	private void validateManualLottoSize(int price, int manual) {
		if (price / UNIT_PRICE < manual) {
			throw new IllegalArgumentException(ERROR_NOT_VALID_MANUAL_LOTTO_SIZE);
		}
	}

	private void validateFullSize() {
		if (this.lottoTickets.size() + 1 > this.sizeOfLottoTickets) {
			throw new IllegalArgumentException(ERROR_CANNOT_ADD);
		}
	}
}
