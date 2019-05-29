package com.woowacourse.lotto.exception;

public class InvalidMoneyException extends RuntimeException {
	public InvalidMoneyException() {
		super("잘못된 구매 금액입니다. 금액을 1000원 단위로 입력해주세요.");
	}
}
