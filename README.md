# 🚀 미션 - 로또

로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다. 로또 1장의 가격은 1000원이다.

## 📈 기능목록

### 입력

- 인아웃풋 test :ps://choichumji.tistory.com/118
- https://regexr.com/5mhou

```text
        String input = "1 + 2 k 3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // 
        
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Out.toString();
```

- [x] 로또 구입 금액을 입력받는다.
    - [x] [예외처리] 숫자가 아닐 경우
    - [x] [예외처리] 1000 미만의 값일 경우
    - [x] [예외처리] 1000으로 나누어 떨어지지 않는 값인 경우

```text
   구입금액을 입력해 주세요.
   14000
   14개를 구매했습니다.
```

- [x] 당첨 번호를 입력받는다.
    - [x] [예외처리] 입력 형식이 아래 예시와 같지 않은 경우

```text
   지난 주 당첨 번호를 입력해 주세요.
   1, 2, 3, 4, 5, 6
```

- [x] 보너스 볼을 입력받는다.
    - [x] [예외처리] 숫자가 아닐 경우

```text
   보너스 볼을 입력해 주세요.
   7
```

### 로또 진행

- [x] 구입 금액을 저장한다.
- [x] 구입 금액/ 1000 의 개수를 생성한다.
- [x] 결과값 개수만큼 로또를 랜덤으로 생성한다.
    - [x] 로또 생성에는 `Collections.shuffle()`을 활용한다.
- [x] 생성된 로또마다
    - [x] 지난주 당첨 번호와 일치하는 번호의 개수를 저장한다.
    - [x] 보너스 볼이 존재하는지 여부를 저장한다.
    - [x] [예외처리] 당첨 번호의 범위가 1 ~ 45를 벗어나는 경우.
    - [x] [예외처리] 보너스 볼의 범위가 1 ~ 45를 벗어나는 경우.
- [x] 당첨 통계를 낸다.
    - [x] 당첨 결과를 계산한다.
    - [x] 총 수익률을 계산한다.

### 로또 통계 출력
- [x] 구매한 로또내역을 출력한다.
- [x] 3개부터 6개까지 일치하는 로또의 개수를 출력한다.
- [x] 수익률과 손해여부를 출력한다.

```text

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)

```

## ✅ TODO

- [x] 인풋 검증 클래스 구현
- [x] 셔플 후 sort 사용
- [x] 상수로 변환
- [x] 숫자를 로또 공 객체로 변환
- [x] 1~45 로또 공 객체를 캐싱해 성능 개선
- [ ] DTO 생성 고민 

## 💻 구조

- [x] controller
  - [x] 메인 컨트롤러
- [x] domain
  - [x] LottoNumber
  - [x] LottoNumbers
  - [x] WinLottoNumbers
  - [x] Money
  - [x] Rank
  - [x] Result
- [x] view
  - [x] InputView
  - [x] OutputView
- [x] utils
  - [x] Constants
  - [x] LottoNumberGenerator
  - [x] Separator
- [x] validator
  - [x] InputValidator
  - [x] LottoNumbersValidator
  - [x] LottoNumberValidator
  - [x] MoneyValidator
  - [x] WinLottoNumbersValidator

## 💕 페어코딩 협의사항

1. exception 클래스를 사용할까?
    1. exception 클래스를 사용해서 하면 깔끔해서 좋은 것 같다! 사용하기로.
2. Dto -> 나중에 필요하면 리팩토링 과정에서 추가하는거로.
3. 전략패턴 -> 테스트 구현하다가 부족할때 추가
4. validator 클래스 따로 빼보기
5. MVC 패턴으로 진행!
