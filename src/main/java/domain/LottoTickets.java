package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
	private static final String ERROR_NOT_DIVISIBLE_BY_UNIT_PRICE = "[ERROR] 금액은 1000원 단위로 나누어 떨어져야 합니다.";
	private static final String ERROR_NOT_POSITIVE = "[ERROR] 금액은 0원일 수 없습니다.";
	private static final int UNIT_PRICE = 1000;
	private final List<Lotto> lottoTickets = new ArrayList<>();

	public LottoTickets(int price) {
		validateDivisibleByThousand(price);
		validatePositive(price);
		int sizeOfLottoTickets = price / UNIT_PRICE;
		for (int nowSizeOfLottoTickets = 0; nowSizeOfLottoTickets < sizeOfLottoTickets; nowSizeOfLottoTickets++) {
			this.lottoTickets.add(new Lotto());
		}
	}

	public int getLottoTicketsSize() {
		return this.lottoTickets.size();
	}

	public List<Lotto> getLottoTickets() {
		return new ArrayList<>(this.lottoTickets);
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
}
