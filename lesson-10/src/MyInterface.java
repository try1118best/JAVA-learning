public interface MyInterface<T> {
    /**
     * 实现泛型接口有两种方式
     * 一种是实现类在定义时继续使用泛型标识，
     * 另一种是实现类在定义时直接给出具体的数据类型
     * @return
     */
    public T getValue();
}
