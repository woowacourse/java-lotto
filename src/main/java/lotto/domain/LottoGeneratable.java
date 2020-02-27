package lotto.domain;

/**
 * 로또 수 생성 인터페이스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public interface LottoGeneratable {
	public static final long LOTTO_PRICE = 1_000;

	LottoTicket generate(LottoPurchaseMoney lottoPurchaseMoney);
}
