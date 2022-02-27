package view.messages;

public enum InputExceptionMessages {
	INVALID_MONEY_RANGE_EXCEPTION("구입 금액의 범위는 1000원~100000원 입니다."),
	INVALID_INPUT_NUMBER_EXCEPTION("구입 금액은 숫자여야 합니다."),
	INVALID_WINNING_NUMBER_EXCEPTION("당첨번호는 \"1,2,3,4,5,6\"과 같은 꼴 이어야 합니다");

	private String message;

	InputExceptionMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
