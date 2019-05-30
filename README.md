# java-lotto
로또 미션 진행을 위한 저장소

## Lotto 1단계 - 자동

### 요구사항

- 기능
    1. 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
    2. 로또 1장의 가격은 1000원이다.

- 프로그래밍
    1. indent(인덴트, 들여쓰기) depth를 2단계에서 1단계로 줄여라.
    2. depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다. else를 사용하지 마라.
    3. 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
    4. method가 한 가지 일만 하도록 최대한 작게 만들어라.
    5. 배열 대신 ArrayList를 사용한다.

### 기능 목록

- 로또 (Lotto)
    1. 6개 번호를 입력받아 Lotto 생성
        * 번호가 6개 되지 않는 경우 Exception
    2. ~~번호의 범위가 1 ~ 45~~
        * 번호 범위는 Number 객체를 통해 예외처리가 필요하다고 판단
    3. 어떤 번호가 Lotto의 번호 목록에 있는지 여부

- 로또 숫자 (LottoNumber)
    1. 번호의 범위가 1~45
        * 범위에 벗어나면 Exception

- 당첨 번호 (WinningNumbers)
    1. 당첨 확인
        * 3개 / 4개 / 5개 / 6개

- 최종 결과 (Result)
    1. 수익률 계산
    
## Lotto 2단계 - 보너스볼

### 요구사항

- 기능
    1. 2등을 위해 추가 번호를 하나 더 추첨한다. 당첨 통계에 2등도 추가해야 한다.
    
- 프로그래밍
    1. enum을 적용하여 프로그래밍 구현
    
### 기능 목록
- 보너스 볼 (BonusBall)
    1. 로또 번호와 마찬가지로 1~45 범위
        * 범위를 벗어나면 Exception
    2. ~~이미 생성된 Lotto의 번호와 중복되는 지 여부~~
        * 중복이 있다면 Exception
        > 보너스 볼에서 Lotto의 번호와 중복 여부를 판단하기 보다 WinningNumber에서 처리하는 것이 적절하다고 판단.
    3. Lotto 번호 중에서 보너스 볼과 일치하는 지 여부 판단
        > 위 로직도 WinningNumber가 수행하는 것이 옳다고 판단.
        >
        > 따라서, LottoNumber 하나를 입력받고 해당 번호가 BonusBall의 번호와 일치하는지 여부 판단으로 변경
        
- 당첨 번호 (WinningNumber)
    1. 보너스 볼이 Lotto 번호와 중복되는 경우
        * 중복이 있다면 Exception
    2. Lotto 번호 중에서 보너스 볼과 일치하는 지 여부 판단

- 당첨 (Prize)
    1. 2등 추가
    
## 리펙토링 (2단계까지 수행 후)

- 중복된 부분이 많음
    1. LottoNumber과 BonusBall의 구현 로직이 비슷함
        LottoNumber가 팩토리 메서드 패턴을 사용하므로 BonusBall에 상속되기에는 부적절
    2. Lotto와 WinningNumber의 인자인 winningNumbers와 공통점이 있어보임
    
    > 위 부분을 상속을 통해 공통로직 중복 제거를 생각해볼 수 있을 것 같음
    
    상속은 부적절하다고 판단, 위임 방식을 적용하려고 시도 (`WinningLotto`)
    [참고](https://wjun.tistory.com/66) 
    
- OutputView에서 printPrizeData 메서드 내부의 if가 많음
    >  interface를 통해 위 if문을 줄일 수 있을지 고려
    
    interface와 Map 자료구조의 조합으로 해결해보려고 했으나 Prize.SECOND, Prize.NONE을 제외하고는
    모두 같은 구조의 메세지를 띔. 기존의 코드에서 Prize.SECOND 부분만 삼항연산자를 통해 해결함

