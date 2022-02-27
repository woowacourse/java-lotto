package domain.exception;

public enum MoneyExceptionMessages {
	INVALID_MONEY_RANGE_EXCEPTION("구입 금액의 범위는 1000원~100000원 입니다.");

	private String message;

	MoneyExceptionMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
