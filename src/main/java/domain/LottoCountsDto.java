package domain;

public class LottoCountsDto {

    private final int manuals;
    private final int randoms;

    public LottoCountsDto(int manuals, int randoms) {
        this.manuals = manuals;
        this.randoms = randoms;
    }

    public int getManuals() {
        return manuals;
    }

    public int getRandoms() {
        return randoms;
    }

    @Override
    public String toString() {
        return "LottoCountsDto{" +
                "manuals=" + manuals +
                ", randoms=" + randoms +
                '}';
    }
}
