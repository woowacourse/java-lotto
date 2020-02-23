package lotto.domain.LottoTicketFactory;

import lotto.domain.AllLottoNumbers;
import lotto.domain.LottoNumber;
import lotto.domain.SerialLottoNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLottoTicketFactory implements LottoTicketFactory {
	protected static final int ZERO_INDEX = 0;
	protected static final int SIX_INDEX = 6;

	private final List<LottoNumber> allLottoNumbers;

	AbstractLottoTicketFactory() {
		allLottoNumbers = new ArrayList<>(AllLottoNumbers.getAll());
	}

	protected List<LottoNumber> getAllLottoNumbers() {
		return allLottoNumbers;
	}

	@Override
	public abstract SerialLottoNumber createLottoTicket();
}
