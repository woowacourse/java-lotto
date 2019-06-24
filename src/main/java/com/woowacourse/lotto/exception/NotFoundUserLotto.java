package com.woowacourse.lotto.exception;

public class NotFoundUserLotto extends RuntimeException {
	public NotFoundUserLotto() {
		super("해당 사용자의 로또 번호를 조회할 수 없습니다.");
	}
}
