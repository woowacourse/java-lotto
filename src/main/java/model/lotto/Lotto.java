package model.lotto;

import model.lottonumber.LottoNumbers;
import model.result.Rank;
import model.winningnumber.WinningLottoNumberDTO;

public class Lotto {
	private final LottoNumbers numbers;

	public Lotto(LottoNumbers numbers) {
		this.numbers = numbers;
	}

	public LottoDTO getLottoDTO() {
		return new LottoDTO(numbers);
	}

	public Rank match(WinningLottoNumberDTO winningLottoNumbersDTO) {
		long matchCount = numbers.countMatchedNumbers(winningLottoNumbersDTO.getWinningNumbers());
		boolean matchBonus = numbers.validateMatchWithBonus(winningLottoNumbersDTO.getBonusBall());
		return Rank.getRank(matchCount, matchBonus);
	}
}
