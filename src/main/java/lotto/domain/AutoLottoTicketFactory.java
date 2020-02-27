package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 로또 번호 자동 생성 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class AutoLottoTicketFactory implements LottoGeneratable {
	private final Random random;

	public AutoLottoTicketFactory(Random random) {
		this.random = random;
	}

	@Override
	public LottoTicket generate(LottoPurchaseMoney lottoPurchaseMoney) {
		List<Lotto> lottos = new ArrayList<>();
		while (lottoPurchaseMoney.canPayable(LOTTO_PRICE)) {
			lottoPurchaseMoney.pay(LOTTO_PRICE);
			lottos.add(generate());
		}
		return new LottoTicket(lottos);
	}

	private Lotto generate() {
		return random.ints(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE + 1)
				.distinct()
				.limit(Lotto.SIZE)
				.mapToObj(LottoNumber::of)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));
	}
}
