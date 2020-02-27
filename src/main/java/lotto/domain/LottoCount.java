package lotto.domain;

import lotto.exception.InvalidRangeException;
import lotto.exception.ManualRangeException;
import lotto.util.StringUtil;

public class LottoCount {
	public static final int ZERO = 0;

	private final int manualLotto;
	private final int autoLotto;

	public LottoCount(String manualLotto, int total) {
		validate(manualLotto);
		validateManualRange(manualLotto, total);
		this.manualLotto = Integer.parseInt(manualLotto);
		this.autoLotto = total - this.manualLotto;
	}

	private void validateManualRange(String manual, int total) {
		if (total - Integer.parseInt(manual) < ZERO) {
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

	public int getManualLotto() {
		return manualLotto;
	}

	public int getAutoLotto() {
		return autoLotto;
	}
}