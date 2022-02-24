package dto;

<<<<<<< HEAD
import domain.Lotto.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottosDto {

    private final int quantity;
    private final List<LottoDto> lottoDtos;

    private LottosDto(List<LottoDto> lottoDtos) {
        this.lottoDtos = new ArrayList<>(lottoDtos);
        this.quantity = lottoDtos.size();
    }

    public static LottosDto from(List<Lotto> lottos) {
=======
import domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottosDto {

    int quantity;
    List<LottoDto> lottoDtos;

    public LottosDto(List<LottoDto> lottoDtos) {
        this.lottoDtos = lottoDtos;
        this.quantity = lottoDtos.size();
    }

    public static LottosDto from(List<Lotto> lottos){
>>>>>>> f80fb84 (feat: DTO 추가)
        List<LottoDto> lottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            LottoDto lottoDto = LottoDto.from(lotto.getLotto());
            lottoDtos.add(lottoDto);
        }
        return new LottosDto(lottoDtos);
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e6945bb (style: 코드 포멧팅)

    public int getQuantity() {
        return quantity;
    }

    public List<LottoDto> getLottoDtos() {
<<<<<<< HEAD
        return Collections.unmodifiableList(lottoDtos);
    }
=======
>>>>>>> f80fb84 (feat: DTO 추가)
=======
        return lottoDtos;
    }
>>>>>>> e6945bb (style: 코드 포멧팅)
}
