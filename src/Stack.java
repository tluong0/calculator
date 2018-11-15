
import java.util.NoSuchElementException;

/*
 * Stack to convert to infix to postfix
 */
/**
 *
 * @author trangluong
 * @param <T>
 */
public class Stack<T> {

    private int top, size;
    private T[] data;

    public Stack(int size) {
        this.top = -1;
        this.size = size;
        this.data = (T[]) new Object[this.size];
    }

    public Stack() {
        this(5);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == (size - 1));
    }

    //try to return peak, if empty, throw exception
    public T peak() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return data[top];
    }

    public boolean push(T element) {
        if (!isFull()) {
            this.data[++top] = element;
            return true;
        } else {
            return false;
        }

    }

    public T pop() {
        T topvalue = null;

        if (!isEmpty()) {

            topvalue = data[top--];

        }
        return topvalue;
    }

    public int count() {
        return (top + 1);
    }

    @Override
    public String toString() {
        String result = "";
        if (!isEmpty()) {
            for (int i = top; i >= 0; i--) {
                result += "|" + data[i] + "|\n";
            }

        } else {
            result = "| |";
        }
        return result;
    }

    public void print() {
        System.out.println(this);
    }
}
