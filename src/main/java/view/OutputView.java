package view;

import model.LottoDTO;

import java.util.List;

public class OutputView {

    private static final String PURCHASE_MESSAGE = "개를 구매했습니다";

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printLottos(List<LottoDTO> lottoStorageDTO) {
        System.out.println(lottoStorageDTO.size() + PURCHASE_MESSAGE);
        lottoStorageDTO
                .forEach(lottoDTO -> System.out.println(lottoDTO.getNumbers()));
    }
}
