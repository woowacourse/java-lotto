package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGroup;

public record LottoGroupDto(List<Lotto> lottoGroup) {

    public static LottoGroupDto from(LottoGroup lottoGroup) {
        return new LottoGroupDto(lottoGroup.getItem());
    }
}
