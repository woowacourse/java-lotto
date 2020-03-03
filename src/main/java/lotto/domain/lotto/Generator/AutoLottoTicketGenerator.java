package lotto.domain.lotto.Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumberCache;

public class AutoLottoTicketGenerator implements LottoTicketGenerator {
	private int countOfAutoLottoTicket;

	public AutoLottoTicketGenerator(int countOfAutoLottoTicket) {
		this.countOfAutoLottoTicket = countOfAutoLottoTicket;
	}

	private Lotto createLotto() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumberCache.values());
		Collections.shuffle(lottoNumbers);
		return lottoNumbers.stream()
			.limit(Lotto.CORRECT_SIZE)
			.collect(Collectors.collectingAndThen(Collectors.toSet(), Lotto::new));
	}

	@Override
	public List<Lotto> generate() {
		List<Lotto> lottoTicket = new ArrayList<>();
		for (int i = 0; i < countOfAutoLottoTicket; i++) {
			lottoTicket.add(createLotto());
		}
		return lottoTicket;
	}
}
