package dto;

import domain.Lotto;
import domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class LottosDto {
    int quantity;
    List<LottoDto> lottoDtos;

    public LottosDto(List<LottoDto> lottoDtos) {
        this.lottoDtos = lottoDtos;
        this.quantity = lottoDtos.size();
    }

    public int getQuantity() {
        return quantity;
    }

    public List<LottoDto> getLottoDtos() {
        return lottoDtos;
    }

    public static LottosDto from(List<Lotto> lottos){
        List<LottoDto> lottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            LottoDto lottoDto = LottoDto.from(lotto.getLotto());
            lottoDtos.add(lottoDto);
        }
        return new LottosDto(lottoDtos);
    }
}
