package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
	List<Integer> numbers = new ArrayList<>();

	public Lotto() {
		for (int i = 1; i <= 6; i++) {
			numbers.add(i);
		}
	}

	public int getNumbersSize() {
		return 6;
	}

	public List<Integer> getNumbers() {
		return this.numbers;
	}
}
