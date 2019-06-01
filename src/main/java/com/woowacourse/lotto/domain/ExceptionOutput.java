package com.woowacourse.lotto.domain;

public enum ExceptionOutput {
	VIOLATE_LOTTO_NUMBER_RANGE("잘못된 로또 넘버입니다."),
	VIOLATE_MONEY("잘못된 구매 금액입니다. 금액을 1000원 단위로 입력해주세요."),
	VIOLATE_PURCHASED_LOTTO("구입 금액보다 로또의 개수가 더 큽니다. 다시 입력해주세요."),
	DUPLICATE_LOTTO_NUMBER("중복된 로또 넘버입니다.");

	private String exceptionMessage;

	ExceptionOutput(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessage() {
		return this.exceptionMessage;
	}
}
