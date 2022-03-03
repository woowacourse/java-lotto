package model.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.bonusball.BonusBallDTO;
import model.lottonumber.LottoNumber;
import model.result.Rank;
import model.winningnumber.LottoWinningNumberDTO;

public class Lotto {

	private final List<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		this.numbers = numbers;
	}

	public LottoDTO getLottoDTO() {
		return new LottoDTO(numbers);
	}

	public Rank match(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumberDTO) {
		long count = countMatchingWinningNumber(winningNumberDTO.getWinningNumbers());

		if (count < Rank.FIFTH.getMatchNumber()) {
			return Rank.FAIL;
		}

		if (count == Rank.SECOND.getMatchNumber()) {
			return matchWithBonus(bonusBallDTO);
		}

		return findRank(count);
	}

	private long countMatchingWinningNumber(List<LottoNumber> winningNumbers) {
		return numbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	private Rank matchWithBonus(BonusBallDTO bonusBallDTO) {
		if (numbers.contains(bonusBallDTO.getNumber())) {
			return Rank.SECOND;
		}
		return Rank.THIRD;
	}

	private Rank findRank(long count) {
		List<Rank> ranks = Arrays.stream(Rank.values())
			.filter(rank -> rank.getMatchNumber() == count)
			.collect(Collectors.toList());
		return ranks.get(0);
	}
}
