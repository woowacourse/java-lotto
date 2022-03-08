package service.dto;

import java.util.List;

public class ManualLottosRequest {
    private List<List<Integer>> manualLottos;

    public ManualLottosRequest(List<List<Integer>> manualLottos) {
        this.manualLottos = manualLottos;
    }

    public List<List<Integer>> getManualLottos() {
        return manualLottos;
    }
}
