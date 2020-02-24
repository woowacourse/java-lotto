package lotto.exceptions;

public class LottoTicketIllegalArgumentException extends IllegalArgumentException {
	private static final String MESSAGE
			= "로또 티켓은 다음을 만족해야합니다." +
			"\n - 로또 번호는 6개로 이루어져야 합니다." +
			"\n - 중복되는 숫자가 없어야합니다.";

	public LottoTicketIllegalArgumentException() {
		super(MESSAGE);
	}
}
