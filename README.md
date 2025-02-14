# java-lotto

로또 미션 저장소

# 요구사항
## 기능 요구사항

- [x] 로또 구매
  - [x] 구매할 티켓 수
    - [x] 로또 1장의 가격은 1000원
  - [x] 로또 번호 랜덤으로 생성
    - [x] 오름차순 정렬
- [x] 로또 당첨 결과
  - [x] 등수 판정
    - [x] 당첨 번호와 보너스볼 기반
  - [x] 총 수익률
    - [x] 당첨 결과들을 종합하여 총 수익률 계산

## 입출력 요구사항

- [x] 입력
  - [x] 구입금액
    - [x] [예외] 빈값, null
    - [x] [예외] 숫자가 아닌 경우
  - [x] 지난 주 당첨 번호
    - [x] [예외] 빈값, null
    - [x] [예외] 숫자가 아닌 경우
  - [x] 보너스 볼
    - [x] [예외] 빈값, null
    - [x] [예외] 숫자가 아닌 경우
- [x] 출력
  - [x] 발행한 로또 티켓(티켓 수)
  - [x] 당첨 통계
    - [x] 등수별 등수 개수
    - [x] 총 수익률

## 예외처리
- [x] 로또 티켓 가격으로 나누어 떨어지지 않는 경우
- [x] 숫자 갯수가 6개가 아닌 경우
- [x] 1~45 밖의 범위 숫자인 경우
- [x] 당첨번호와 보너스번호가 중복되는 경우
- [x] 당첨번호 끼리 중복되는 경우

## 실행 결과
```
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

## 프로그래밍 요구 사항
모든 로직에 단위 테스트를 구현한다. 단, UI(System.out, System.in) 로직은 제외
자바 코드 컨벤션을 지키면서 프로그래밍한다.
참고문서: https://google.github.io/styleguide/javaguide.html 또는 https://myeonguni.tistory.com/1596
규칙 1: 한 메서드에 오직 한 단계의 들여쓰기(indent)만 한다.를 지키며 구현한다.
예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메소드)를 분리하면 된다.
규칙 2: else 예약어를 쓰지 않는다.를 지키며 구현한다.
힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
함수(또는 메소드)의 길이가 15라인을 넘어가지 않도록 구현한다.
함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.

## 기능 목록 및 commit 로그 요구사항
기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

## 피드백 내용
- [x] README를 체크 리스트로 작성하기
- [x] ErrorMessage를 enum으로 작성한 이유
  - [x] Lotto와 LottoNumber의 상수에 의존하기 때문에, 추후 유지보수가 어려워지진 않을지?
- [x] 상수와 상태 줄바꿈  
- [x] use는 bank의 기준에서 어떤 책임을 수행하는가?
- [x] rank 객체에게 메시지를 보내보는 건 어떨지? (getter 사용 지양)
- [x] 금액 계산에 대해서는 double 대신에 BigDecimal 권장
- [x] 세 자리수 마다 _를 명시적으로 선언해 가독성 높이기
- [x] TreeSet을 활용한 이유
- [x] record 클래스의 특징 확인하기
- [x] generator를 Lottos의 상태에 둔 이유는?
- [x] for안의 i < payment / Lotto.PRICE 조건에서 payment / Lotto.PRICE 계산 식을 변수 값으로 대체
- [x] getter 사용 지양(메서드명 포함)