package com.woowacourse.lotto.exception;

public class NotFoundLottoResult extends RuntimeException {
	public NotFoundLottoResult() {
		super("해당 회차의 당첨 결과를 조회할 수 없습니다.");
	}
}
