package model.bonusball;

public class BonusBallDTO {
	private final int number;

	public BonusBallDTO(int number) {
		this.number = Integer.valueOf(number);
	}

	public int getNumber() {
		return Integer.valueOf(number);
	}
}
