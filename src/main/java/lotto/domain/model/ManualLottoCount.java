package lotto.domain.model;


import lotto.exception.InvalidRangeException;
import lotto.util.StringUtil;

public class ManualLottoCount {
	public static final int ZERO = 0;

	private final int manualLottoCount;

	public ManualLottoCount(String input) {
		validate(input);
		manualLottoCount = Integer.parseInt(input);
	}

	public void validate(String input) {
		StringUtil.checkNull(input);
		StringUtil.checkBlank(input);
		StringUtil.checkNumberFormat(input);
		checkRange(input);
	}

	private void checkRange(String input) {
		if (Integer.parseInt(input) < ZERO) {
			throw new InvalidRangeException("음수는 사용할 수 없습니다.");
		}
	}
}
