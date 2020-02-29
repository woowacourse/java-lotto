package lotto.domain.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lotto.domain.lotto.Generator.ManualLottoTicketGenerator;
import lotto.domain.lotto.Lotto;

public class NumberLinesOfManualLotto {
	private List<String> numberLines;

	public NumberLinesOfManualLotto() {
		numberLines = new ArrayList<>();
	}

	public void add(String rawLine) {
		validate(rawLine);
		numberLines.add(rawLine);
	}

	private void validate(String rawLine) {
		validateNull(rawLine);
		List<String> numbers = Arrays.asList(rawLine.split(ManualLottoTicketGenerator.DELIMITER));
		validateSize(numbers);
		validateInteger(numbers);
		validateLottoNumberType(numbers);
	}

	private void validateNull(String rawLine) {
		if (Objects.isNull(rawLine)) {
			throw new InvalidManualNumberLineException(InvalidManualNumberLineException.NULL);
		}
	}

	private void validateSize(List<String> numberLine) {
		if (numberLine.size() != Lotto.CORRECT_SIZE) {
			throw new InvalidManualNumberLineException(InvalidManualNumberLineException.WRONG_SIZE);
		}
	}

	private void validateInteger(List<String> numberLine) {
		try {
			numberLine.stream()
				.map(String::trim)
				.forEach(Integer::parseInt);
		} catch (NumberFormatException e) {
			throw new InvalidManualNumberLineException(InvalidManualNumberLineException.NOT_INTEGER);
		}
	}

	private void validateLottoNumberType(List<String> numberLine) {
		try {
			numberLine.stream()
				.map(String::trim)
				.forEach(LottoNumber::valueOf);
		} catch (InvalidLottoNumberException e) {
			throw new InvalidManualNumberLineException(e.getMessage());
		}
	}

	public List<String> getNumberLines() {
		return numberLines;
	}

	public int size() {
		return numberLines.size();
	}
}
