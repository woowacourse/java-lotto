package lotto.domain;

public class RandomLottoTicketFactory {
	private final RandomGenerator randomGenerator;

	public RandomLottoTicketFactory(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public SerialLottoNumber create() {
		return new SerialLottoNumber(randomGenerator.generateSixNumbers());
	}
}
