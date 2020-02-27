package lotto.domain.lottoTicket;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.result.MatchCount;

public class LottoTicket {

	public static final int TOTAL_SIZE = 6;
	private static final String DELIMITER = ",";

	private final Set<LottoNumber> lottoNumbers;

	public LottoTicket(List<LottoNumber> lottoNumbers) {
		validate(lottoNumbers);
		this.lottoNumbers = new TreeSet<>(lottoNumbers);
	}

	public static LottoTicket valueOf(String inputLottoNumbers) {
		return Arrays.stream(inputLottoNumbers.split(DELIMITER))
			.map(String::trim)
			.map(LottoNumber::valueOf)
			.collect(collectingAndThen(toList(), LottoTicket::new));
	}

	private void validate(List<LottoNumber> lottoNumbers) {
		validateNullOrEmpty(lottoNumbers);
		validateSize(lottoNumbers);
		validateDuplication(lottoNumbers);
	}

	private void validateNullOrEmpty(List<LottoNumber> lottoNumbers) {
		if (Objects.isNull(lottoNumbers) || lottoNumbers.isEmpty()) {
			throw new InvalidLottoTicketException(InvalidLottoTicketException.NULL_OR_EMPTY);
		}
	}

	private void validateSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != TOTAL_SIZE) {
			throw new InvalidLottoTicketException(InvalidLottoTicketException.WRONG_SIZE);
		}
	}

	private void validateDuplication(List<LottoNumber> lottoNumbers) {
		long distinctSize = new HashSet<>(lottoNumbers).size();

		if (distinctSize != lottoNumbers.size()) {
			throw new InvalidLottoTicketException(InvalidLottoTicketException.DUPLICATION);
		}
	}

	public boolean contains(LottoNumber bonusLottoNumber) {
		return lottoNumbers.contains(bonusLottoNumber);
	}

	public MatchCount countMatchingWith(LottoTicket winningLottoTicket) {
		List<LottoNumber> matchingLottoNumbers = new ArrayList<>(lottoNumbers);
		matchingLottoNumbers.retainAll(winningLottoTicket.lottoNumbers);
		return new MatchCount(matchingLottoNumbers.size());
	}

	public Set<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		LottoTicket that = (LottoTicket)object;
		return Objects.equals(lottoNumbers, that.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumbers);
	}

}
