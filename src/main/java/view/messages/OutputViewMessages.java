package view.messages;

public enum OutputViewMessages {
	RESULT_STATISTICS("당첨 통계\n--------\n"),
	RETURN_RATE("총 수익률은 %.2f 입니다.");

	private String message;

	OutputViewMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
