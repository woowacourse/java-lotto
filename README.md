# java-lotto
로또 미션 진행을 위한 저장소

## TODO
### 1단계           

## DONE
### 1단계
    * 구입 금액을 입력받아야 한다.
        > Money의 역할
        * 입력받은 값이 blank(null, "", " ")이면 예외 반환 
        * 입력받은 값이 숫자가 아니면 예외 반환
        * 입력받은 값이 1,000원 미만이거나 100,000원 이상이 예외 반환
        * 구매 가능한 로또의 수 반환
        
    * 로또를 구매 가능한 갯수만큼 생성해야 한다.
        > LottoNumbers의 역할
        * 가능한 LottoNumber 객체를 모두 가짐
        * LottoNumber를 shuffle 하여 랜덤하게 6개 LottoNumber List 반환

    * 로또를 구매 가능한 갯수만큼 생성해야 한다.
        > Lottos의 역할
        * 구매 가능한 개수만큼 로또 객체 생성 & List에 저장
    
    * 지난주 당첨 번호를 입력받아야 한다.
        > Lotto의 역할
        * 입력받은 값이 6개가 아니면 예외 반환
        * 입력받은 Lotto(winning)와 몇개의 숫자가 일치하는지 반환
        * 일치 갯수에 맞는 랭킹 enum 객체 반환
        
        > WinningLotto의 역할
        * 입력받은 값이 blank(null, "", " ")이면 예외 반환 
        * 입력받은 값들 중 숫자가 아닌 값이 있으면 예외 반환
        
        > LottoNumbers의 역할
        * 입력받은 값에 맞는 LottoNumber를 List 반환
        * 입력받은 값이 6개가 아니면 예외 반환
        * 입력받은 값에 해당하는 LottoNumber가 없으면 예외 반환
        
        > LottosResult의 역할
        * 구매한 로또들의 결과 Map으로 저장
        * 수익율 반환