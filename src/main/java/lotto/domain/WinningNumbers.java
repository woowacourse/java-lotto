package lotto.domain;

import java.util.List;

public class WinningNumbers extends Lotto {
	public WinningNumbers(List<Number> numbers) {
		super(numbers);
	}

	public boolean contains(Number bonusNumber) {
		if (numbers.contains(bonusNumber)) {
			return false;
		}
		return true;
	}
}
