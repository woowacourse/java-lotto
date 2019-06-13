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

## 로또 3단계 - 수동구매

### 요구사항

- 기능
    1. 현재 로또 생성기는 자동 생성 기능만 제공한다. 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
    2. 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.
    
- 프로그래밍
    1. 예외가 발생하는 부분에 대해 자바 Exception을 적용해 예외처리한다.
    2. 사용자가 입력한 값에 대한 예외 처리를 철저히 한다.
    
### 기능 목록

- 수동 로또
    1. 수동으로 포멧에 맞게 입력하여 로또 생성
        * 수동으로 구매하는 로또의 수가 구입 금액보다 큰 경우 예외처리
        * 중복된 번호 입력시 예외처리
        * 1~45 범위를 넘어선 경우 예외처리
        * ", "로 번호를 구분하지 않는 경우 예외처리

## 로또 4단계 - web UI

### 요구사항

- 기능
    1. 콘솔 UI로 구현되어 있는 로또를 웹 UI로 동작하도록 구현한다.
    2. 웹 화면은 콘솔 UI와 같은 기능으로 구현하며, 각자의 힘으로 구현해 본다. 자신만의 독특한 UI도 환영한다.

- 프로그래밍
    1. 콘솔 UI를 웹 UI로 변경할 때 도메인 객체를 최대한 변경하지 않는다.
    2. [프로그래밍 체크리스트](https://github.com/woowacourse/woowacourse-docs/blob/master/cleancode/pr_checklist.md)의 원칙을 지키면서 프로그래밍 한다.
    
### 기능 목록

※ 참고! Console Application 흐름
1. 구매하려는 액수 입력
2. 수동 구매 갯수 입력
3. 수동 구매 번호 입력
4. 자동 구매
5. 당첨 번호 입력
6. 결과 출력

- web UI
> `Console Application`에서 view 부분을 web front가 담당하도록 만들 예정
    
1. index.html (get: `/`)

주어진 형식에 따라 로또를 만들 수 있는 페이지

2. post: `/lotto`

index.html에서 입력받은 값으로 lotto를 진행
한번에 모든 입력값을 `/lotto`로 보내 최종 결과값 `/result`로 보여주는 구조

## 로또 5단계 - DB 적용

### 요구사항

- 기능
    1. 각 회차별로 사용자가 구매한 로또, 당첨 번호, 당첨 결과, 당첨 금액, 수익률을 조회할 수 있어야 한다.
    2. 현실에서는 매주 1회차가 진행되는데 이 로또는 로또 게임을 진행할 때마다 1회차가 증가하는 것으로 가정한다.
    
- 프로그래밍
    1. [프로그래밍 체크리스트](https://github.com/woowacourse/woowacourse-docs/blob/master/cleancode/pr_checklist.md)의 원칙을 지키면서 프로그래밍 한다.
    
### DB

DB 단계에서는 회차 개념이 존재, 따라서 회차 정보를 저장할 테이블이 필요

![lotto-erd](https://user-images.githubusercontent.com/30178507/59393168-6e431d80-8db5-11e9-98fb-58f8a183a969.PNG)

> winning_lotto 테이블은 WinningNumber 클래스의 정보를 담고 있습니다.
>
> 현재 미션의 편의를 위해 로또를 구성하는 6개의 숫자를 모두 bought_lotto와 winning_lotto가 담고 있도록 만들었습니다.

하나의 lotto_game마다 result와 winning_lotto는 각각 하나씩이므로 각각 1:1 대응을 이루도록 구성.

1. DB에 접근하기 위한 DAO 클래스 설계
    > lotto와 lottoGame에 대한 Dao 설계
2. DTO 사용고려
    일급 컬랙션과 원자값을 감싼 불변 클래스의 값을 db에 저장해야하는데 이때 메서드 호출(getter)이 너무 많아지므로 사용 고려
    > 각 테이블 당 하나의 dto를 설계
3. DB 저장에 따른 WEB 뷰 라우팅 구성 변경
    - '/' (get) : 모든 회차의 결과를 간략하게 보여주는 페이지
    - '/result/:round' (get) : 해당 round의 결과를 보여주는 페이지
    - '/lotto' (get) : 로또 작성 페이지
    - '/lotto' (post) : 작성 값을 토대로 lotto game을 실행하는 route
4. service layer
    - service 객체로 각 Dao가 수행하는 작업들을 비즈니스 로직에 따라 관리
    - 라운드 생성 및 조회
    - 로또 생성 및 조회
    - 당첨 번호 생성 및 조회
    - 결과 생성 및 조회
 
## 현재 화면 구성

> 추후 스타일링할 예정입니다,,,

1. `/` (처음 메인 페이지, 로또를 생성하는 페이지)

![로또 생성 페이지](https://user-images.githubusercontent.com/30178507/59416765-01557500-8e01-11e9-9768-132f6a794b4a.PNG)

2. `/result/:round` (해당 라운드의 결과를 보여주는 페이지)

![결과 페이지](https://user-images.githubusercontent.com/30178507/59416766-0286a200-8e01-11e9-8b60-970540e1e599.PNG)
