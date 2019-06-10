package lotto.domain.lotto.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinningLottoDTO {
    private static final String SEPERATOR = ",";
    List<Integer> lottoNumbers = new ArrayList<>();

    public void set(String winningLotto) {
        String[] tokens = winningLotto.split(SEPERATOR);
        for (String token : tokens) {
            lottoNumbers.add(Integer.parseInt(token));
        }
    }

    public List<Integer> getWinningLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
