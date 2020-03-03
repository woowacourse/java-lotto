package lotto.domain.lotto;

public class CountOfManualLottoTicket {
	private static final int MIN_BOUND = 0;

	private int countOfManualLotto;

	public CountOfManualLottoTicket(String rawCount, int countOfAllLotto) {
		validateType(rawCount);
		validateBound(countOfAllLotto);
	}

	private void validateType(String rawCount) {
		try {
			countOfManualLotto = Integer.parseInt(rawCount);
		} catch (IllegalArgumentException e) {
			throw new InvalidCountOfManualLottoTicketException(InvalidCountOfManualLottoTicketException.WRONG_TYPE);
		}
	}

	private void validateBound(int maxBound) {
		if (countOfManualLotto < MIN_BOUND || countOfManualLotto > maxBound) {
			throw new InvalidCountOfManualLottoTicketException(InvalidCountOfManualLottoTicketException.WRONG_BOUND);
		}
	}

	public int getCountOfManualLotto() {
		return countOfManualLotto;
	}
}
