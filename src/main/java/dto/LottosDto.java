package dto;

import java.util.List;

public class LottosDto {

    private List<LottoDto> lottoDtos;

    public LottosDto(List<LottoDto> lottoDtos) {
        this.lottoDtos = lottoDtos;
    }

    public List<LottoDto> getLottoDtos() {
        return lottoDtos;
    }
}
