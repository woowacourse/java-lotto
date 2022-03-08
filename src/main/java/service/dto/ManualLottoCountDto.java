package service.dto;

public class ManualLottoCountDto {
    private int manualLottoCountAmount;

    public ManualLottoCountDto(Integer manualLottoCountAmount) {
        this.manualLottoCountAmount = manualLottoCountAmount;
    }

    public int getCount() {
        return manualLottoCountAmount;
    }
}
