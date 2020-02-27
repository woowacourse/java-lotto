package lotto.view.dto;

import java.util.List;

public class ManualNumbersDTO {
    private List<List<Integer>> lists;

    public ManualNumbersDTO(List<List<Integer>> lists) {
        this.lists = lists;
    }

    public List<List<Integer>> getLists() {
        return lists;
    }
}
