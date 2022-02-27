package model.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.bonusball.BonusBallDTO;
import model.result.Rank;
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

	public Rank match(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumberDTO) {
		List<Integer> winningNumbers = winningNumberDTO.getWinningNumbers();
		long count = countMatchingWinningNumber(winningNumbers);

		if (count == CHECKING_BONUS_NUMBER) {
			return matchWithBonus(bonusBallDTO);
		}

		return findRank(count);
	}

	private long countMatchingWinningNumber(List<Integer> winningNumbers) {
		return numbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	private Rank matchWithBonus(BonusBallDTO bonusBallDTO) {
		if (numbers.contains(bonusBallDTO.getNumber())) {
			return Rank.BONUS;
		}
		return Rank.FIVE;
	}

	private Rank findRank(long count) {
		List<Rank> ranks = Arrays.stream(Rank.values())
			.filter(rank -> rank.getMatchNumber() == count)
			.collect(Collectors.toList());
		return ranks.get(0);
	}
}
