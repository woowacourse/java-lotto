package lotto.domain.lotto;

class InvalidCountOfManualLottoTicketException extends IllegalArgumentException {
	static final String WRONG_BOUND = "수동 로또 갯수는 0보다 작거나 구매한 로또 갯수보다 클 수 없습니다.";
	static final String WRONG_TYPE = "숫자를 입력해주세요";

	InvalidCountOfManualLottoTicketException(String s) {
		super(s);
	}
}
