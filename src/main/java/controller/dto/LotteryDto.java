package controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.LotteryNumber;

public class LotteryDto {

	private final List<Integer> numbers;

	private LotteryDto(final List<LotteryNumber> lotteryNumbers) {
		numbers = lotteryNumbers.stream()
			.map(LotteryNumber::getLotteryNumber)
			.collect(Collectors.toList());
	}

	public static LotteryDto fromEntity(final List<LotteryNumber> numbers) {
		return new LotteryDto(numbers);
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
