package com.woowacourse.lotto.exception;

public class NotFoundWinningLotto extends RuntimeException {
	public NotFoundWinningLotto() {
		super("해당 회차의 당첨 번호를 조회할 수 없습니다.");
	}
}
