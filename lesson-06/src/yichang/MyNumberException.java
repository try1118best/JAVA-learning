package yichang;

public class MyNumberException extends Exception{
    public MyNumberException(String error){
        super(error);
    }
    /*这个构造方法接受一个字符串参数 error，
    * 并将其传递给父类 Exception 的构造方法。
    * 这个字符串参数通常用于提供错误信息，描述异常的具体原因。*/
}
