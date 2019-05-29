package com.woowacourse.lotto.view;

public enum UserOutput {
	DEMAND_MONEY_FOR_PURCHASHE_LOTTO("구입금액을 입력해 주세요.");

	String userOutputMessage;

	UserOutput(String userOutputMessage) {
		this.userOutputMessage = userOutputMessage;
	}

	public String getUserOutputMessage() {
		return userOutputMessage;
	}
}
