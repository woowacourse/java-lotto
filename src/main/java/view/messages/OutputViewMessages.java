package view.messages;

public enum OutputViewMessages {
	RESULT_STATISTICS("당첨 통계\n--------\n"),
	RETURN_RATE("총 수익률은 %.2f 입니다."),
	RANKING_MESSAGE("%d개 일치 (%d원) - %d개"),
	RANKING_SECOND_MESSAGE("%d개 일치, 보너스 볼 일치 (%d원) - %d개"),
	EXCEPTION_PREFIX("[ERROR] ");

	private String message;

	OutputViewMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
