package study.effectivejava.item8;

import java.lang.ref.Cleaner;

public class Room implements AutoCloseable{
    private static final Cleaner cleaner = Cleaner.create();
    // cleanable 객체. 수거 대상이 되면 방을 청소한다.
    private final Cleaner.Cleanable cleanable;
    // 방의 상태. cleanable과 공유한다.
    private final State state;

    // 청소가 필요한 자원. 절대 Room을 참조해서는 안된다
    private static class State implements Runnable{

        private int numJunkPiles;

        public State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // close 메서드나 cleaner가 호출한다.
        @Override
        public void run() {
            System.out.println("방 청소");
            numJunkPiles = 0;
        }
    }

    public Room(int numJunkPiles) {
        this.state = new State(numJunkPiles);
        this.cleanable = cleaner.register(this, this.state);
    }

    @Override
    public void close() throws Exception {
        this.cleanable.clean();
    }
}
