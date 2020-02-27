package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lotto.util.StringUtil;

public class ManualLottoTicketsFactory implements LottoTicketsFactory {
	private static final String MANUAL_LOTTO_COUNT_NOT_EQUAL_EXPECTED_EXCEPTION_MESSAGE = "입력하신 번호의 사이즈가 일치하지 않습니다.";
	private static final String NULL_REFERENCE_EXCEPTION_MESSAGE = "NULL 객체가 허용되지 않습니다.";

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
			throw new NullPointerException(NULL_REFERENCE_EXCEPTION_MESSAGE);
		}
	}

	private void validateSameLength(List<String> manualLottoNumbers, LottoCount lottoCount) {
		if (lottoCount.isNonRightCount(manualLottoNumbers.size())) {
			throw new IllegalArgumentException(MANUAL_LOTTO_COUNT_NOT_EQUAL_EXPECTED_EXCEPTION_MESSAGE);
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
