package lotto.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import lotto.exception.InvalidLottoException;

public class Lotto implements Iterable<Number> {
	private final List<Number> numbers;

	public Lotto(List<Number> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	private void validate(List<Number> numbers) {
		checkSize(numbers);
		checkDuplication(numbers);
	}

	private void checkDuplication(List<Number> numbers) {
		if(new HashSet<>(numbers).size() != numbers.size()) {
			throw new InvalidLottoException("로또 번호는 중복 될 수 없습니다.");
		}
	}

	private void checkSize(List<Number> numbers) {
		if(numbers.size() != 6){
			throw new InvalidLottoException("로또는 6개의 수를 가져야 합니다.");
		}
	}

	public Iterator<Number> iterator(){
		return numbers.iterator();
	}
}
