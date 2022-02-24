package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
	private final List<Lotto> lottoTickets = new ArrayList<>();

	public LottoTickets(int price) {
		validateDivisibleByThousand(price);
		validatePositive(price);
		price /= 1000;
		while (price-- > 0) {
			this.lottoTickets.add(new Lotto());
		}
	}

	public int getLottoTicketsSize() {
		return this.lottoTickets.size();
	}

	public List<Lotto> getLottoTickets() {
		return new ArrayList<>(this.lottoTickets);
	}

	private static void validateDivisibleByThousand(int price) {
		if (price % 1000 != 0) {
			throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위로 나누어 떨어져야 합니다.");
		}
	}

	private static void validatePositive(int price) {
		if (price == 0) {
			throw new IllegalArgumentException("[ERROR] 금액은 0원일 수 없습니다.");
		}
	}
}
