package view.messages;

public enum OutputViewMessages {
	RESULT_STATISTICS("당첨 통계\n--------\n"),
	RETURN_RATE("총 수익률은 %.2f 입니다."),
	RANKING_MESSAGE("%d개 일치 (%d원) - %d개"),
	RANKING_SECOND_MESSAGE("%d개 일치, 보너스 볼 일치 (%d원) - %d개"),
	PURCHASED_LOTTERY_MESSAGE("수동으로 %d장, 자동으로 %d개를 구매했습니다."),
	EXCEPTION_PREFIX("[ERROR] ");

	private String message;

	OutputViewMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
