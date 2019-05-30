package com.woowacourse.lotto.view;

public enum UserOutput {
	DEMAND_MONEY_FOR_PURCHASE_LOTTO("구입금액을 입력해 주세요."),
	DEMAND_WINNING_LOTTO("지난 주 당첨 번호를 입력해주세요."),
	DEMAND_BONUS_BALL("보너스 볼을 입력해 주세요."),
	DEMAND_COUNT_OF_MANUALLY_LOTTO("수동으로 구매할 로또 수를 입력해 주세요."),
	DEMAND_MANUAL_LOTTO("수동으로 구매할 번호를 입력해 주세요."),
	PRINT_NUMBER_OF_LOTTO("%d개를 구매했습니다.\n"),
	PRINT_EARNINGS_RATE("총 수익률은 %d%%" + " 입니다.\n"),
	PRINT_LOTTO_MATCH_SECOND("%d개 일치, 보너스 볼 일치(%d원)- %d개\n"),
	PRINT_LOTTO_MATCH_RESULT("%d개 일치 (%d원)- %d개\n");

	String userOutputMessage;

	UserOutput(String userOutputMessage) {
		this.userOutputMessage = userOutputMessage;
	}

	public String getUserOutputMessage() {
		return userOutputMessage;
	}
}
