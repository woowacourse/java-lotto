package model.lottonumber;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbersDTO {
	private final List<LottoNumber> lottoNumbers;

	public LottoNumbersDTO(List<LottoNumber> lottoNumbers) {
		this.lottoNumbers = new ArrayList<>(lottoNumbers);
	}

	public List<LottoNumber> getLottoNumbers() {
		return new ArrayList<>(lottoNumbers);
	}
}
