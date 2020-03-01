package lotto.domain.lottogenerator;

import lotto.domain.Customer;
import lotto.domain.Lotto;
import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGeneratorManual implements LottoGenerator {
	private static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";

	private static List<Lotto> createManualLotto(String[] manualLottoNumbers) {
		List<Lotto> lottos = new ArrayList<>();
		for (String numbers : manualLottoNumbers) {
			String[] winLottoNumbers = StringUtils.splitByComma(numbers);
			List<LottoNo> lotto = toLottoNos(winLottoNumbers);
			lottos.add(new Lotto(lotto));
		}
		return lottos;
	}

	private static List<LottoNo> toLottoNos(String[] winLotto) {
		try {
			return Arrays.stream(winLotto)
					.map(Integer::parseInt)
					.map(LottoNo::toLottoNo)
					.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
		}
	}

	@Override
	public List<Lotto> generator(Customer customer) {
		return createManualLotto(customer.getManualLottoNumbers());
	}
}
