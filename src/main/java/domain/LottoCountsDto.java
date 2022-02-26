package domain;

public class LottoCountsDto {

    private final int manuals;
    private final int autos;

    public LottoCountsDto(int manuals, int randoms) {
        this.manuals = manuals;
        this.autos = randoms;
    }

    public int getManuals() {
        return manuals;
    }

    public int getAutos() {
        return autos;
    }

    @Override
    public String toString() {
        return "LottoCountsDto{" +
                "manuals=" + manuals +
                ", randoms=" + autos +
                '}';
    }
}
