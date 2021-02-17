package view.dto;

public class LottoCountResponseDto {
    private final int lottoCount;

    public LottoCountResponseDto(final int lottoCount) {
        this.lottoCount = lottoCount;
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
