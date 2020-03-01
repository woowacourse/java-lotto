package lotto.domain.purchase;

import java.util.Objects;

public class LottoMoney {

	public static final LottoMoney ZERO = new LottoMoney(0);

	private static final int UNIT = 1_000;

	private final int lottoMoney;

	public LottoMoney(int lottoMoney) {
		validate(lottoMoney);
		this.lottoMoney = lottoMoney;
	}

	public static LottoMoney valueOf(String inputLottoMoney) {
		try {
			validateNullOrEmpty(inputLottoMoney);
			return new LottoMoney(Integer.parseInt(inputLottoMoney));
		} catch (NumberFormatException e) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_INTEGER);
		}
	}

	private static void validateNullOrEmpty(String inputLottoMoney) {
		if (Objects.isNull(inputLottoMoney) || inputLottoMoney.isEmpty()) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NULL_OR_EMPTY);
		}
	}

	private void validate(int lottoMoney) {
		validateNegative(lottoMoney);
		validateUnit(lottoMoney);
	}

	private void validateNegative(int lottoMoney) {
		if (lottoMoney < 0) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NEGATIVE);
		}
	}

	private void validateUnit(int lottoMoney) {
		if (isDivideByUnit(lottoMoney)) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.INVALID_UNIT);
		}
	}

	private boolean isDivideByUnit(int lottoMoney) {
		return (lottoMoney % UNIT) != 0;
	}

	public PurchasingCount generatePurchasingLottoTicketCount() {
		return new PurchasingCount(lottoMoney / UNIT);
	}

	public LottoMoney addBy(LottoMoney addedLottoMoney) {
		return new LottoMoney(this.lottoMoney + addedLottoMoney.lottoMoney);
	}

	public LottoMoney multiplyBy(int operand) {
		return new LottoMoney(this.lottoMoney * operand);
	}

	public int measureWinningRate(LottoMoney inputLottoMoney) {
		if (inputLottoMoney.lottoMoney == 0) {
			return 0;
		}
		return (this.lottoMoney * 100) / inputLottoMoney.lottoMoney;
	}

	public int getLottoMoney() {
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
