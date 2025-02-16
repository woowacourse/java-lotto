package domain.dto;

import domain.Rank;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResultDto {

    private final Map<Rank, Integer> viewResult;

    private LottoResultDto(Map<Rank, Integer> result) {
        this.viewResult = new LinkedHashMap<>(result);
    }

    public static LottoResultDto from(Map<Rank, Integer> result) {
        return new LottoResultDto(result);
    }

    public Map<Rank, Integer> getViewResult() {
        return Collections.unmodifiableMap(viewResult);
    }
}
