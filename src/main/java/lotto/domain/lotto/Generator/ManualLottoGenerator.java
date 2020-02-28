package lotto.domain.lotto.Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.NumberLinesOfManualLotto;

public class ManualLottoGenerator implements LottosGenerator {
	public static final String DELIMITER = ",";

	private List<String> numberLines;

	public ManualLottoGenerator(NumberLinesOfManualLotto manualLottoNumbers) {
		numberLines = manualLottoNumbers.getNumberLines();
	}

	private Lotto createLotto(String numberLine) {
		return new Lotto(Arrays.stream(numberLine.split(DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.map(LottoNumber::valueOf)
			.collect(Collectors.toSet()));
	}

	@Override
	public Lottos generate() {
		List<Lotto> lottos = new ArrayList<>();
		for (String numberLine : numberLines) {
			lottos.add(createLotto(numberLine));
		}
		return new Lottos(lottos);
	}
}
