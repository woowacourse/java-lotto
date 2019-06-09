package lotto.domain.lotto.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersDTO {
    private final static String SEPERATOR = ",";
    List<Integer> lottoNumbers = new ArrayList<>();

    public void set(String queryParams) {
        String[] tokens = queryParams.split(SEPERATOR);
        for (String token : tokens) {
            lottoNumbers.add(Integer.parseInt(token));
        }
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
