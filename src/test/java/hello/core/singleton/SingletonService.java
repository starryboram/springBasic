package hello.core.singleton;

public class SingletonService {

    //JVM 뜰 때, Static 영역에 있는 new SingletonService를 내부적으로 실행하고 생성해서 자기 자신을 인스턴스에 넣어놓는다.
    private static final SingletonService instance = new SingletonService(); // 인스턴스를 꺼낼 수 있는 애는

    public static SingletonService getInstance(){ // 얘(getInstance 메서드)밖에 없음
        return instance;
    }

    private SingletonService(){} // private으로 설정해서 아무나 못만들게 만들고

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
