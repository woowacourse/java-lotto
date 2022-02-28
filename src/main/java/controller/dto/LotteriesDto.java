package controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.Lottery;
import domain.LotteryNumber;

public class LotteriesDto {

	private final List<LotteryDto> lotteries;

	private LotteriesDto(final List<List<LotteryNumber>> lotteryNumbers) {
		this.lotteries = lotteryNumbers.stream()
			.map(LotteryDto::fromEntity)
			.collect(Collectors.toList());
	}

	public static LotteriesDto fromEntity(final List<Lottery> lotteries) {
		return new LotteriesDto(lotteries.stream()
			.map(Lottery::getNumbers)
			.collect(Collectors.toList()));
	}

	public List<LotteryDto> getLotteries() {
		return lotteries;
	}
}
