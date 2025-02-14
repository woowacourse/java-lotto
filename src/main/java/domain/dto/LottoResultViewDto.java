package domain.dto;

import domain.Rank;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResultViewDto {

    private final Map<Rank, Integer> viewResult;

    private LottoResultViewDto(Map<Rank, Integer> result) {
        this.viewResult = new LinkedHashMap<>(result);
    }

    public static LottoResultViewDto from(Map<Rank, Integer> result) {
        return new LottoResultViewDto(result);
    }

    public Map<Rank, Integer> getViewResult() {
        return Collections.unmodifiableMap(viewResult);
    }
}
