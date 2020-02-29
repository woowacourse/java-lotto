package lotto.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 로또 번호 수동 생성 클래스
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
	public LottoTicket generate(PurchaseMoney purchaseMoney) {
		List<Lotto> lottoTicket = new ArrayList<>();
		for (String lottoNumber : lottoTicketNumbers) {
			purchaseMoney.pay(LOTTO_PRICE);
			lottoTicket.add(Lotto.ofComma(lottoNumber));
		}
		return new LottoTicket(lottoTicket);
	}

	boolean canNotPayable(PurchaseMoney lottoPurchaseMoney) {
		long purchaseMoney = calculatePurchaseMoney();
		return lottoPurchaseMoney.canNotPayable(purchaseMoney);
	}

	private long calculatePurchaseMoney() {
		return lottoTicketNumbers.size() * LOTTO_PRICE;
	}
}
