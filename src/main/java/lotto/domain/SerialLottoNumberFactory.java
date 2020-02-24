package lotto.domain;

import lotto.exceptions.LottoTicketIllegalArgumentException;
import lotto.utils.StringParser;

import java.util.List;
import java.util.stream.Collectors;

public class SerialLottoNumberFactory {
	public static SerialLottoNumber createRandomLottoTicket(RandomGenerator randomGenerator)
			throws LottoTicketIllegalArgumentException {
		return new SerialLottoNumber(randomGenerator.generateSixNumbers());
	}

	public static SerialLottoNumber createWinningLottoNumbers(String input)
			throws IllegalArgumentException {
		List<Integer> integers = StringParser.stringToIntegerList(input);
		List<LottoNumber> lottoNumbers = integers.stream()
				.map(AllLottoNumbers::getLottoNumber)
				.collect(Collectors.toUnmodifiableList());

		return new SerialLottoNumber(lottoNumbers);
	}
}
