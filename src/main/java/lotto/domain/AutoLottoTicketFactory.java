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
	private static final Random RANDOM = new Random();

	@Override
	public LottoTicket generate(Money money) {
		List<Lotto> lottos = new ArrayList<>();
		for (long count = money.calculateBuyCount(); count > 0; --count) {
			lottos.add(generate());
		}
		return new LottoTicket(lottos);
	}

	private Lotto generate() {
		return RANDOM.ints(LottoNumber.MIN_VALUE, LottoNumber.MAX_VAULE + 1)
				.distinct()
				.limit(Lotto.SIZE)
				.mapToObj(LottoNumber::of)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));
	}
}
