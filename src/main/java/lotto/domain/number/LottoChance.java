package lotto.domain.number;

public class LottoChance {

    private final Chance passiveChance;
    private final Chance activeChance;

    public LottoChance(final Chance passiveChance, final Chance activeChance) {
        this.passiveChance = passiveChance;
        this.activeChance = activeChance;
    }

    public int getPassiveChance() {
        return passiveChance.unwrap();
    }

    public int getActiveChance() {
        return activeChance.unwrap();
    }
}
