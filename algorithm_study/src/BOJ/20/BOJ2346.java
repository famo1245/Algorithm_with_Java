import java.util.Scanner;

public class BOJ2346 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        IDeque<int[]> deque = new IDeque<>(N + 2);
        int nextPosition = sc.nextInt();

        for (int i = 0; i < N - 1; i++) {
            // {풍선 번호, 다음 위치}
            deque.pushRight(new int[]{(i + 2), sc.nextInt()});
        }

        StringBuilder sb = new StringBuilder();
        // 1부터 풍선을 터트림
        sb.append("1 ");

        int[] next = {};
        while (!deque.isEmpty()) {
            if (nextPosition > 0) {
                for (int i = 1; i < nextPosition; i++) {
                    int[] temp = deque.popLeft();
                    deque.pushRight(temp);
                }

                next = deque.popLeft();
            } else {
                for (int i = 1; i < -nextPosition; i++) {
                    int[] temp = deque.popRight();
                    deque.pushLeft(temp);
                }

                next = deque.popRight();
            }

            nextPosition = next[1];
            sb.append(next[0]);
            sb.append(" ");
        }

        System.out.println(sb.toString());
        sc.close();
    }
}

// Dequeue 구현
class IDeque<E> {

    private static final int INIT_SIZE = 64;

    private E[] deque;
    private int size;
    private int capacity;

    private int front;
    private int rear;

    // default constructor
    public IDeque() {
        this.deque = (E[]) new Object[INIT_SIZE];
        this.capacity = INIT_SIZE;
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    // capacity 를 지정한 경우 constructor
    public IDeque(int capacity) {
        this.deque = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public void show() {
        for (E e : deque) {
            System.out.println(e);
        }
    }

    public void pushLeft(E e) {
        if ((front - 1 + capacity) % capacity != rear) {
            deque[front] = e;
            front = (front - 1 + capacity) % capacity;
            size++;
        }
    }

    public void pushRight(E e) {
        if ((rear + 1) % capacity != front) {
            rear = (rear + 1) % capacity;
            deque[rear] = e;
            size++;
        }
    }

    public E popLeft() {
        if (isEmpty()) {
            return null;
        }

        front = (front + 1) % capacity;
        E item = deque[front];
        deque[front] = null;
        size--;
        return item;
    }

    public E popRight() {
        if (isEmpty()) {
            return null;
        }

        E result = deque[rear];
        deque[rear] = null;
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
