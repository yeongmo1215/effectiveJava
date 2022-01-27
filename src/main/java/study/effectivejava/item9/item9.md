# ITEM 9 try-finally 보다는 try-with-resource 를 사용하라

자바 라이브러리에는 close 메서드를 호출해 직접 닫아줘야 하는 자원이 많다(ex. InputStream, OutputSteam, java.sql.Connection, ...)
close는 클라잉너트가 놓치기 쉬워서 예측할수 없느 ㄴ성능 문제로 이어지기도 한다.
finalizer는 아이템 8에서와 같이 안전하지 못하다.

###try-finally의 단점
* 코드가 더러워짐.
* try문에서 발생한 Exception이 finally에서 발생한 Exception에게 덮혀 디버깅이 힘들다.

###try-with-resource
AutoClosable 인터페이스 상속하여 close 메소드 구현
* 코드가 간결해짐.
* 첫번째 발생한 Exception이 기록됨(숨겨진 예외들은 스택 추적 내역에 숨겨졌다(suppressed)는 꼬리표를 달고 출력되어 확인가능/Throwable에 추가된 getSuppressed 메서드를 이용하면 프로그램코드에서 확인 가능)
