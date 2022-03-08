package model.lotto;

import model.lottonumber.LottoNumbers;
import model.result.Rank;
import model.winningnumber.WinningLottoNumberDTO;

public class Lotto {
	private final LottoNumbers numbers;

	public Lotto(LottoNumbers numbers) {
		this.numbers = numbers;
	}

	public Rank match(WinningLottoNumberDTO winningLottoNumbersDTO) {
		long matchCount = numbers.countMatchedNumbers(winningLottoNumbersDTO.getWinningNumbers());
		boolean matchBonus = numbers.containLottoNumber(winningLottoNumbersDTO.getBonusBall());
		return Rank.getRank(matchCount, matchBonus);
	}

	public LottoNumbers getNumbers() {
		return numbers;
	}

	@Override
	public String toString() {
		return numbers.toString();
	}
}
