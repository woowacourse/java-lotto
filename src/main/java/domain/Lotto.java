package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
	List<LottoNumber> numbers = new ArrayList<>();

	public Lotto() {
		for (int i = 1; i <= 6; i++) {
			numbers.add(LottoNumber.from(i));
		}
	}

	public int getNumbersSize() {
		return 6;
	}

}
