package lotto.domain.lottoMoney;

import java.util.Objects;

public class LottoMoney {
	public static final LottoMoney ZERO = new LottoMoney(0L);

	private static final long UNIT = 1_000L;

	private final long lottoMoney;

	public LottoMoney(long lottoMoney) {
		validate(lottoMoney);
		this.lottoMoney = lottoMoney;
	}

	private void validate(long lottoMoney) {
		validateNegative(lottoMoney);
		validateUnit(lottoMoney);
	}

	private void validateNegative(long lottoMoney) {
		if (lottoMoney < 0L) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NEGATIVE);
		}
	}

	private void validateUnit(long lottoMoney) {
		if (isDivideByUnit(lottoMoney)) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.INVALID_UNIT);
		}
	}

	private boolean isDivideByUnit(long lottoMoney) {
		return (lottoMoney % UNIT) != 0L;
	}

	public static LottoMoney valueOf(String inputLottoMoney) {
		try {
			validateNullOrEmpty(inputLottoMoney);
			return new LottoMoney(Long.parseLong(inputLottoMoney));
		} catch (NumberFormatException e) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_INTEGER);
		}
	}

	private static void validateNullOrEmpty(String inputLottoMoney) {
		if (Objects.isNull(inputLottoMoney) || inputLottoMoney.isEmpty()) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NULL_OR_EMPTY);
		}
	}

	public PurchasingCount generatePurchasingLottoTicketCount() {
		return new PurchasingCount(lottoMoney / UNIT);
	}

	public LottoMoney addBy(LottoMoney addedLottoMoney) {
		return new LottoMoney(this.lottoMoney + addedLottoMoney.lottoMoney);
	}

	public LottoMoney multiplyBy(long operand) {
		return new LottoMoney(this.lottoMoney * operand);
	}

	public long measureWinningRate(LottoMoney inputLottoMoney) {
		if (inputLottoMoney.lottoMoney == 0L) {
			return 0L;
		}
		return (this.lottoMoney * 100L) / inputLottoMoney.lottoMoney;
	}

	public long getLottoMoney() {
		return lottoMoney;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		LottoMoney that = (LottoMoney)object;
		return lottoMoney == that.lottoMoney;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoMoney);
	}
}
