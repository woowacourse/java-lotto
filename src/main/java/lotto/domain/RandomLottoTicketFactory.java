package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoTicketFactory {
	public static final int ZERO_INDEX = 0;
	public static final int SIX_INDEX = 6;

	public static SerialLottoNumber createLottoTicket() {
		List<LottoNumber> allLottoNumbers = new ArrayList<>(AllLottoNumbers.getAll());
		Collections.shuffle(allLottoNumbers);
		return new SerialLottoNumber(allLottoNumbers.subList(ZERO_INDEX, SIX_INDEX));
	}
}
