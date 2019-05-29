package com.woowacourse.lotto.view;

public enum UserOutput {
	DEMAND_MONEY_FOR_PURCHASE_LOTTO("구입금액을 입력해 주세요."),
	DEMAND_WINNING_LOTTO("지난 주 당첨 번호를 입력해주세요."),
	PRINT_NUMBER_OF_LOTTO("%d개를 구매했습니다.\n");

	String userOutputMessage;

	UserOutput(String userOutputMessage) {
		this.userOutputMessage = userOutputMessage;
	}

	public String getUserOutputMessage() {
		return userOutputMessage;
	}
}
