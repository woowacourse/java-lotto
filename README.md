# java-lotto
로또 미션 진행을 위한 저장소

## 클래스 목록
- money
  - 구입 금액 
- Lotto

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

- [x] 입력한 금액을 1,000원으로 나눠서 몫을 int 로 반환

- [x] 6개의 겹치지 않는 숫자를 리스트로 생성
  - [ ] int 로 입력받으면 그 수만큼 복권 생성
  
- [x] 이번주 당첨번호(WinLotto) 를 리스트로 입력받아 저장
  - [x] string 으로 당첨번호를 입력받아 리스트로 반환
  - [x] 보너스볼 입력 받기
- [ ] 로또들을 주면 구매한 번호 List 와 비교
  - [ ] 로또 하나를 List 로 주면 WinLotto 와 비교하여 INT 반환
  
- [ ] 전체 구매한 번호와 당첨번호를 비교 몇개 일치한게 몇개인지 LIST로 반환

- [ ] 수익률 계산하기


## 프로그래밍 요구 사항
* indent(인덴트, 들여쓰기) depth를 2단계에서 1단계로 줄여라.
* depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
* else를 사용하지 마라.
* 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
* method가 한 가지 일만 하도록 최대한 작게 만들어라.
* 배열 대신 ArrayList를 사용한다.
* enum을 적용해 프로그래밍을 구현한다.
