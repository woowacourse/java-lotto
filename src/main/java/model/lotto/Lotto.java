package model.lotto;

import java.util.Arrays;
import java.util.List;

import model.bonusball.BonusBallDTO;
import model.result.RateOfReturn;
import model.result.Statistics;
import model.winningnumber.LottoWinningNumberDTO;

public class Lotto {
	private static final int CHECKING_BONUS_NUMBER = 5;

	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public LottoDTO getLottoDTO() {
		return new LottoDTO(numbers);
	}

	public void checkWithWinningNumberAndBonus(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumberDTO,
		RateOfReturn rateOfReturn) {
		List<Integer> winningNumbers = winningNumberDTO.getWinningNumbers();
		long count = countMatchingWinningNumber(winningNumbers);

		if (count == CHECKING_BONUS_NUMBER) {
			checkWithBonusBallAndStore(bonusBallDTO, rateOfReturn);
			return;
		}
		storeResult(count, rateOfReturn);
	}

	private long countMatchingWinningNumber(List<Integer> winningNumbers) {
		return numbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	private void checkWithBonusBallAndStore(BonusBallDTO bonusBallDTO, RateOfReturn rateOfReturn) {
		if (numbers.contains(bonusBallDTO.getNumber())) {
			rateOfReturn.saveResult(Statistics.BONUS);
			return;
		}
		rateOfReturn.saveResult(Statistics.FIVE);
	}

	private void storeResult(long count, RateOfReturn rateOfReturn) {
		Arrays.stream(Statistics.values())
			.filter(statistics -> statistics.getMatchNumber() == count)
			.forEach(statistics -> rateOfReturn.saveResult(statistics));
	}
}
