package lotto.domain.number;

public class Chance {

    private static final int CHANCE_MIN = 0;

    private final int chance;

    public Chance(int chance) {
        this.chance = chance;
    }

    public static Chance valueOf(String chance) {
        int convertedChance = validateNumeric(chance);
        validateRange(convertedChance);

        return new Chance(convertedChance);
    }

    private static int validateNumeric(String chance) {
        try {
            return Integer.parseInt(chance);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력이 숫자가 아니거나 Integer 범위를 벗어났습니다.");
        }
    }

    private static void validateRange(int chance) {
        if (chance < CHANCE_MIN) {
            throw new IllegalArgumentException("불가능한 로또 개수 입니다.");
        }
    }

    public int unwrap() {
        return chance;
    }
}
