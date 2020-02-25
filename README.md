# java-lotto
로또 미션 진행을 위한 저장소

## 클래스 목록
- money
  - 구입 금액 
- Lotto
- LottoNo

- LottoFactory

- LottoManager
  - 당첨 복권과 비교, 당첨 통계
  
- WinLotto

- BonusBall

- inputView

- outputView

- main <- controller

## 기능 구현 목록
- [x] 구입 금액 입력

- [x] 수동으로 구입할 로또 개수 입력

- [x] 전달받은 수만큼 사용자에게 로또 번호를 입력받아 List<LottoNo> 로 반환
  - [x] 전달받은 수만큼 로또 번호 String 으로 입력받기
  - [x] 입력받은 String 형식의 로또 번호를 '\n' 기준으로 스플릿하여 저장
  - [x] 입력받은 String[] 형식의 로또 번호들을 List<LottoNo> 로 저장

- [x] 입력한 금액을 1,000원으로 나눠서 몫을 int 로 반환

- [x] 6개의 겹치지 않는 숫자를 리스트로 생성
  - [x] int 로 입력받으면 그 수만큼 복권 생성
  
- [x] 이번주 당첨번호(WinLotto) 를 리스트로 입력받아 저장
  - [x] string 으로 당첨번호를 입력받아 리스트로 반환
  - [x] 보너스볼 입력 받기
- [x] 로또들을 주면 구매한 번호 List 와 비교
  - [x] 로또 하나를 List 로 주면 WinLotto 와 비교하여 맞은 갯수를 INT 반환
  - [x] 위에서 반환받은 인트로 Result 의 카운터 +1
  
- [x] 전체 구매한 번호와 당첨번호를 비교 몇개 일치한게 몇개인지 LIST로 반환

- [x] 수익률 계산하기


## 프로그래밍 요구 사항
* indent(인덴트, 들여쓰기) depth를 2단계에서 1단계로 줄여라.
* depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
* else를 사용하지 마라.
* 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
* method가 한 가지 일만 하도록 최대한 작게 만들어라.
* 배열 대신 ArrayList를 사용한다.
* enum을 적용해 프로그래밍을 구현한다.


## 예외
- [x] Money 에서 1000원 미만의 금액이 전달
- [x] Money 에서 숫자가 아닌 값이 전달
- [x] Lotto 에서 List 의 size 가 6이 아닐 때
- [x] Lotto 에서 List 에 1 ~ 45의 숫자가 아닐 때
- [x] 보너스볼에서 1 ~ 45의 숫자가 아닐 때
- [x] 보너스볼과 Lotto 에서 숫자가 아닌 값일 때
