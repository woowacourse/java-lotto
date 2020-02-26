package lotto.domain;

import lotto.exception.InvalidRangeException;
import lotto.exception.ManualRangeException;
import lotto.util.StringUtil;

public class LottoCount {
	public static final int ZERO = 0;

	private final int manualLotto;
	private final int autoLotto;

	public LottoCount(String manual, int total) {
		validate(manual);
		validateManualRange(manual, total);
		manualLotto = Integer.parseInt(manual);
		this.autoLotto = total - manualLotto;
	}

	private void validateManualRange(String manual, int total) {
		if (total - Integer.parseInt(manual) < 0) {
			throw new ManualRangeException("수동입력은" + total + "이하로만 가능합니다.");
		}
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