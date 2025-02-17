## 패키지 구조도

```
main
└───java
    ├───AppConfig.java                      // 애플리케이션 구성 데이터
    ├───Application.java                    // 메인 애플리케이션
    ├───common                              
    │   ├───constant
    │   │   ├───NumberConstants.java        // 숫자 상수
    │   │   └───RegexConstants.java         // 정규 표현식 상수
    │   └───utils
    │       └───ValidationUtils.java        // 범용적인 유효성 검증 메서드 객체        
    ├───controller
    │   └───Controller.java                 // 컨트롤러
    ├───model
    │   ├───LottoWinningNumbers.java        // 당첨 번호, 보너스 번호 관리 객체
    │   ├───PurchasedLotto.java             // 구매한 로또 내역
    │   ├───lotto
    │   │   ├───Lotto.java                  // 로또 관리 객체
    │   │   ├───LottoMachine.java           // 로또 생성 머신
    │   │   └───RandomNumberGenerator.java  // 난수 생성기
    │   └───result
    │       ├───PrizeResult.java            // 당첨 결과 관리 객체
    │       ├───Rank.java                   // 당첨 정보 관리 객체
    │       └───WinningDiscriminator.java   // 당첨 여부 판독기
    ├───service
    │   └───parser
    │       ├───BonusNumberParser.java      // 보너스 번호 파서
    │       ├───BudgetParser.java           // 예산 파서
    │       └───WinningNumberParser.java    // 당첨 번호 파서
    ├───validator
    │   ├───ErrorMessages.java              // 예외 메시지 enum
    │   └───Validator.java                  // 유효성 검사기
    └───view
        ├───DisplayConstants.java           // 입출력 prompt enum
        ├───InputView.java                  // 입력 객체
        └───OutputView.java                 // 출력 객체
```