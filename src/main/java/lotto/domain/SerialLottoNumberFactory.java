package lotto.domain;

import lotto.exceptions.LottoTicketIllegalArgumentException;
import lotto.utils.StringParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SerialLottoNumberFactory {
	private static final int ZERO_INDEX = 0;
	private static final int SIX_INDEX = 6;

	public static SerialLottoNumber createRandomLottoTicket() throws LottoTicketIllegalArgumentException {
		List<LottoNumber> allLottoNumbers = new ArrayList<>(AllLottoNumbers.getAll());
		Collections.shuffle(allLottoNumbers);

		return new SerialLottoNumber(allLottoNumbers.subList(ZERO_INDEX, SIX_INDEX));
	}

	public static SerialLottoNumber createWinningLottoNumbers(String input) throws IllegalArgumentException {
		List<Integer> integers = StringParser.stringToIntegerList(input);
		List<LottoNumber> lottoNumbers = integers.stream()
				.map(LottoNumber::new)
				.collect(Collectors.toUnmodifiableList());

		return new SerialLottoNumber(lottoNumbers);
	}
}
