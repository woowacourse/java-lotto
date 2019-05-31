package com.woowacourse.lotto.view;

public enum UserOutput {
	DEMAND_MONEY_FOR_LOTTO_PURCHASE("구입금액을 입력해 주세요."),
	DEMAND_WINNING_LOTTO("지난 주 당첨 번호를 입력해주세요."),
	DEMAND_BONUS_BALL("보너스 볼을 입력해 주세요."),
	DEMAND_COUNT_OF_MANUALLY_LOTTO("수동으로 구매할 로또 수를 입력해 주세요."),
	DEMAND_MANUAL_LOTTO("수동으로 구매할 번호를 입력해 주세요."),
	PRINT_EARNINGS_RATE("총 수익률은 %d%%" + " 입니다.\n"),
	PRINT_SECOND_OF_LOTTO("%d개 일치, 보너스 볼 일치(%d원)- %d개\n"),
	PRINT_RESULT_OF_LOTTO("%d개 일치 (%d원)- %d개\n"),
	PRINT_COUNT_OF_PURCHASED_LOTTO("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n");

	String userOutputMessage;

	UserOutput(String userOutputMessage) {
		this.userOutputMessage = userOutputMessage;
	}

	public String getUserOutputMessage() {
		return userOutputMessage;
	}
}
