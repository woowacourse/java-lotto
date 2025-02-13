package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Lotto;

public record LottoDto(
        List<Integer> lotto
) {

    public static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.getLotto());
    }

    public static LottoDto getSortedLottoDto(Lotto lotto) {
        List<Integer> lottoNumbers = new ArrayList<>(lotto.getLotto());
        Collections.sort(lottoNumbers);
        return new LottoDto(lottoNumbers);
    }

    @Override
    public String toString() {
        List<String> lottoStrings = new ArrayList<>(lotto.size());
        for (int lottoNumber : lotto) {
            lottoStrings.add(String.valueOf(lottoNumber));
        }
        String lottoString = String.join(", ", lottoStrings);
        return "[" + lottoString + "]";
    }
}
