package lotto.domain.LottoTicketFactory;

import lotto.domain.SerialLottoNumber;

import java.util.Collections;

public class RandomLottoTicketFactory extends AbstractLottoTicketFactory {
	@Override
	public SerialLottoNumber createLottoTicket() {
		Collections.shuffle(super.getAllLottoNumbers());
		return new SerialLottoNumber(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
