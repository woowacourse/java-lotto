package domain;

import util.LottoNumbersGenerator;

import java.util.List;

import static constant.LottoConstant.NUMBER_FOR_BONUS_CHECK;

public class Lotto {

	List<Integer> numbers;

	public Lotto(LottoNumbersGenerator lottoNumbersGenerator) {
		this.numbers = lottoNumbersGenerator.generate();
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public int getNumbersSize() {
		return this.numbers.size();
	}

	public ResultStatics calculate(AnswerLotto answerLotto) {
		int count = (int) answerLotto.getNumbers().stream().filter(num -> this.numbers.contains(num)).count();
		boolean bonus = false;

		if (count == NUMBER_FOR_BONUS_CHECK) {
			bonus = this.numbers.contains(answerLotto.getBonusNumber());
		}

		return ResultStatics.of(count, bonus);
	}
}
