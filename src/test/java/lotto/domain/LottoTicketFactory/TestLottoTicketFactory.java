package lotto.domain.LottoTicketFactory;

import lotto.domain.SerialLottoNumber;

public class TestLottoTicketFactory extends AbstractLottoTicketFactory {

	@Override
	public SerialLottoNumber createLottoTicket() {
		return new SerialLottoNumber(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
