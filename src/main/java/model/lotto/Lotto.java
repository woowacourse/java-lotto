package model.lotto;

import model.bonusball.BonusBallDTO;
import model.lottonumber.LottoNumbers;
import model.result.Rank;
import model.winningnumber.LottoWinningNumberDTO;

public class Lotto {
	private final LottoNumbers numbers;

	public Lotto(LottoNumbers numbers) {
		this.numbers = numbers;
	}

	public LottoDTO getLottoDTO() {
		return new LottoDTO(numbers);
	}

	public Rank match(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumberDTO) {
		long matchCount = numbers.countMatchedNumbers(winningNumberDTO.getWinningNumbers());
		boolean matchBonus = numbers.validateMatchWithBonus(bonusBallDTO.getNumber());
		return Rank.getRank(matchCount, matchBonus);
	}
}
