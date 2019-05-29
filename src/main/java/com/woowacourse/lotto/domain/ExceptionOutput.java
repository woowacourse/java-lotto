package com.woowacourse.lotto.domain;

public enum ExceptionOutput {
	VIOLATE_LOTTO_NUMBER_RANGE("잘못된 로또 넘버입니다."),
	VIOLATE_MONEY("잘못된 구매 금액입니다. 금액을 1000원 단위로 입력해주세요."),
	VIOLATE_INDEX("잘못된 인덱스 접근입니다."),
	DUPLICATE_LOTTO_NUMBER("중복된 로또 넘버입니다.");

	private String exceptionMessage;

	ExceptionOutput(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessage() {
		return this.exceptionMessage;
	}
}
