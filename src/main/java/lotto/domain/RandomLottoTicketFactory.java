package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoTicketFactory {
	public static final int ZERO_INDEX = 0;
	public static final int SIX_INDEX = 6;
	private final List<LottoNumber> allLottoNumbers;

	public RandomLottoTicketFactory() {
		allLottoNumbers = new ArrayList<>(AllLottoNumbers.get());
	}

	public LottoTicket createLottoTicket() {
		Collections.shuffle(allLottoNumbers);
		return new LottoTicket(allLottoNumbers.subList(ZERO_INDEX, SIX_INDEX));
	}
}
