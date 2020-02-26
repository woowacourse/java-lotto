package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lotto.util.StringUtil;

public class ManualLottoTicketsFactory implements LottoTicketsFactory {
	private final List<String> manualLottoNumbers;
	private final LottoCount lottoCount;

	public ManualLottoTicketsFactory(List<String> manualLottoNumbers, LottoCount lottoCount) {
		validate(manualLottoNumbers, lottoCount);
		this.manualLottoNumbers = Collections.unmodifiableList(new ArrayList<>(manualLottoNumbers));
		this.lottoCount = lottoCount;
	}

	private void validate(List<String> manualLottoNumbers, LottoCount lottoCount) {
		validateNull(manualLottoNumbers, lottoCount);
		validateSameLength(manualLottoNumbers, lottoCount);
	}

	private void validateNull(List<String> manualLottoNumbers, LottoCount lottoCount) {
		if (Objects.isNull(manualLottoNumbers) || Objects.isNull(lottoCount)) {
			throw new NullPointerException();
		}
	}

	private void validateSameLength(List<String> manualLottoNumbers, LottoCount lottoCount) {
		if (lottoCount.isNonRightCount(manualLottoNumbers.size())) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public LottoTickets create() {
		List<LottoTicket> result = new ArrayList<>();
		for (int i = 0; lottoCount.isNonFullCount(i); i++) {
			String[] numbers = StringUtil.splitRawLottoNumbers(manualLottoNumbers.get(i));
			result.add(LottoTicket.of(numbers));
		}
		return new LottoTickets(result);
	}
}
