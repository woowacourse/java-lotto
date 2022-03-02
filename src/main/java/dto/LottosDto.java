package dto;

import domain.Lotto.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottosDto {

    int manualQuantity;
    int autoQuantity;
    List<LottoDto> lottoDtos;

    public LottosDto(List<LottoDto> lottoDtos, int manualQuantity) {
        this.lottoDtos = lottoDtos;
        this.manualQuantity = manualQuantity;
        this.autoQuantity = lottoDtos.size() - manualQuantity;
    }

    public static LottosDto from(List<Lotto> lottos, int manualQuantity) {
        List<LottoDto> lottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            LottoDto lottoDto = LottoDto.from(lotto.getLotto());
            lottoDtos.add(lottoDto);
        }
        return new LottosDto(lottoDtos, manualQuantity);
    }

    public int getAutoQuantity() {
        return autoQuantity;
    }

    public int getManualQuantity() {
        return manualQuantity;
    }

    public List<LottoDto> getLottoDtos() {
        return lottoDtos;
    }
}
