package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {
	private final List<int[]> lottoNumbersStored;
	private int index = 0;

	public ManualLottoGenerator(List<int[]> numbersSequence) {
		this.lottoNumbersStored = numbersSequence;
	}

	public ManualLottoGenerator(int[] numbers) {
		lottoNumbersStored = new ArrayList<>();
		lottoNumbersStored.add(numbers);
	}

	@Override
	public Lotto createLotto() {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int number : lottoNumbersStored.get(index++)) {
			lottoNumbers.add(new LottoNumber(number));
		}
		return new Lotto(lottoNumbers);
	}
}
