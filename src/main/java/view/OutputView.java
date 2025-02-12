package view;

import java.util.List;
import view.dto.LottosDTO;
import view.dto.LottosDTO.LottoDTO;
import view.dto.ResultDTO;

public class OutputView {

    public void printLottos(LottosDTO lottosDTO) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%d개를 구매했습니다.", lottosDTO.lottoDTOs().size()));
        stringBuilder.append('\n');
        lottosDTO.lottoDTOs().forEach(lottoDTO -> appendStringBuilder(lottoDTO, stringBuilder));
        System.out.println(stringBuilder);
    }

    public void printResult(ResultDTO resultDTO) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer>
        stringBuilder.append(String.format("""
                당첨 통계
                ---------
                %d개 일치 (%d원)- %d개
                %d개 일치 (%d원)- %d개
                %d개 일치 (%d원)- %d개
                %d개 일치, 보너스 볼 일치(%d원) - %d개
                %d개 일치 (%d원)- %d개
                총 수익률은 0.f2입니다.
                """, resultDTO.));


    }

    public void appendStringBuilder(LottoDTO lottoDTO, StringBuilder stringBuilder) {
        String format = "[%d, %d, %d, %d, %d, %d]";
        stringBuilder.append(String.format(format, lottoDTO.numbers().toArray())).append('\n');
    }


}
