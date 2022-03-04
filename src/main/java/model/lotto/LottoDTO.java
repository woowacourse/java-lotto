package model.lotto;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumbers;
import model.lottonumber.LottoNumbersDTO;

public class LottoDTO {
	private final LottoNumbers numbers;

	public LottoDTO(LottoNumbers numbers) {
		this.numbers = LottoNumbers.valueOf(numbers);
	}

	public List<Integer> getNumbers() {
		LottoNumbersDTO lottoNumbersDTO = numbers.getLottoNumbersDTO();
		return lottoNumbersDTO.getLottoNumbers()
			.stream()
			.map(lottoNumber -> lottoNumber.getNumber())
			.collect(Collectors.toList());
	}
}
