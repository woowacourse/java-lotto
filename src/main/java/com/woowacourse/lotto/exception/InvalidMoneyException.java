package com.woowacourse.lotto.exception;

import com.woowacourse.lotto.domain.ExceptionOutput;

public class InvalidMoneyException extends RuntimeException {
	public InvalidMoneyException() {
		super(ExceptionOutput.VIOLATE_MONEY.getExceptionMessage());
	}
}
