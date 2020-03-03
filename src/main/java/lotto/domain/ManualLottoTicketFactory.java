package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 로또 번호 수동 생성 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/27
 */
public class ManualLottoTicketFactory implements LottoGenerative {
	private final List<Lotto> lottoNumbers;

	public ManualLottoTicketFactory(List<String> lottoNumbers) {
		this.lottoNumbers = lottoNumbers.stream()
				.map(Lotto::ofComma)
				.collect(Collectors.toList());
	}

	@Override
	public LottoTicket generate(PurchaseMoney purchaseMoney) {
		List<Lotto> lottoTicket = new ArrayList<>();
		for (Lotto lotto : lottoNumbers) {
			purchaseMoney.pay(LOTTO_PRICE);
			lottoTicket.add(lotto);
		}
		return new LottoTicket(lottoTicket);
	}
}
