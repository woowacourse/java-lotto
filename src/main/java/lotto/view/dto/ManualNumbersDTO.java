package lotto.view.dto;

import java.util.List;

public class ManualNumbersDTO {
    private List<List<Integer>> manualNumbers;

    public ManualNumbersDTO(List<List<Integer>> manualNumbers) {
        this.manualNumbers = manualNumbers;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
