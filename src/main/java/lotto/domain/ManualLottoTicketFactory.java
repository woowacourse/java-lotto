package lotto.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/27
 */
public class ManualLottoTicketFactory implements LottoGeneratable {
	private List<String> lottoTicketNumbers;

	public ManualLottoTicketFactory(List<String> lottoTicketNumbers) {
		this.lottoTicketNumbers = lottoTicketNumbers;
	}

	@Override
	public LottoTicket generate(LottoPurchaseMoney lottoPurchaseMoney) {
		List<Lotto> lottoTicket = new ArrayList<>();
		for (String lottoNumber : lottoTicketNumbers) {
			lottoPurchaseMoney.pay(LOTTO_PRICE);
			lottoTicket.add(Lotto.ofComma(lottoNumber));
		}
		return new LottoTicket(lottoTicket);
	}
}
