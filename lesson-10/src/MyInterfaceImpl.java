public class MyInterfaceImpl<T> implements MyInterface<T> {

    private T obj;

    public T getObj() {
        return obj;
    }

    public MyInterfaceImpl(T obj) {
        this.obj = obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public T getValue() {
        return null;
    }
}
