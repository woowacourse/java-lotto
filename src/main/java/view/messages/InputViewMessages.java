package view.messages;

public enum InputViewMessages {
	INPUT_MONEY_MESSAGE("구입금액을 입력해 주세요."),
	INPUT_WINNING_NUMBER_MESSAGE("지난 주 당첨 번호를 입력해 주세요."),
	INPUT_BONUS_BALL_MESSAGE("보너스 볼을 입력해 주세요."),
	INPUT_NUM_OF_MANUAL_LOTTERY_MESSAGE("수동으로 구매할 로또 수를 입력해 주세요."),
	INPUT_MANUAL_LOTTERY_NUMBER_MESSAGE("수동으로 구매할 번호를 입력해 주세요.");

	private String message;

	InputViewMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
