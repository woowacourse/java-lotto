package lotto.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.model.prize.PrizeInformation;

public class PrizeInformationDTO {
	private final int matchingCount;
	private final Boolean bonus;
	private final int amount;
	private final int prizeCount;

	private PrizeInformationDTO(int matchingCount, Boolean bonus, int amount, int prizeCount) {
		this.matchingCount = matchingCount;
		this.bonus = bonus;
		this.amount = amount;
		this.prizeCount = prizeCount;
	}

	public static List<PrizeInformationDTO> from(PrizeInformation prizeInformation) {
		return prizeInformation.getKeys().stream()
			.map(prize -> new PrizeInformationDTO(
				prize.getMatchCount(), prize.isBonus(), prize.getAmount(), prizeInformation.getCount(prize)))
			.collect(Collectors.toList());
	}

	public int getMatchingCount() {
		return matchingCount;
	}

	public Boolean getBonus() {
		return bonus;
	}

	public int getAmount() {
		return amount;
	}

	public int getPrizeCount() {
		return prizeCount;
	}
}
