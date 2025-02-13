package view;

import view.dto.LottosDTO;
import view.dto.LottosDTO.LottoDTO;
import view.dto.ResultDTO;
import view.dto.ResultDTO.PrizeDTO;

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
        stringBuilder.append("당첨 통계\n");
        stringBuilder.append("---------\n");

        resultDTO.prizeDTOs().stream().sorted()
                .forEach(prizeDTO -> stringBuilder
                        .append(generatePrizeDetail(prizeDTO))
                        .append("\n")
                );

        stringBuilder.append(generateProfit(resultDTO.profit()));

        System.out.println(stringBuilder);
    }

    public String generateProfit(double profit){
        return String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", profit);
    }

    public String generatePrizeDetail(PrizeDTO prizeDTO){
        String bonusComment = " ";
        if (prizeDTO.isBonus()){
            bonusComment = ", 보너스 볼 일치";
        }
        return String.format("%d개 일치%s(%d원)- %d개",
                prizeDTO.count(),
                bonusComment,
                prizeDTO.price(),
                prizeDTO.match());
    }

    public void appendStringBuilder(LottoDTO lottoDTO, StringBuilder stringBuilder) {
        String format = "[%d, %d, %d, %d, %d, %d]";
        stringBuilder.append(String.format(format, lottoDTO.numbers().toArray())).append('\n');
    }


}
