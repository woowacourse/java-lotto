package lotto.domain;

import lotto.exceptions.LottoTicketIllegalArgumentException;

public class RandomLottoTicketFactory {
	private final RandomGenerator randomGenerator;

	public RandomLottoTicketFactory(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public SerialLottoNumber create()
			throws LottoTicketIllegalArgumentException {
		return new SerialLottoNumber(randomGenerator.generateSixNumbers());
	}
}
