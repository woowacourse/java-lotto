package lotto.domain;

/**
 * 로또 수 생성 인터페이스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public interface LottoGenerative {
	PurchaseMoney LOTTO_PRICE = new PurchaseMoney(1_000L);

	LottoTicket generate(PurchaseMoney purchaseMoney);
}
