public class MyInterfaceImpl2 implements MyInterface<String>{

    private String obj;

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public MyInterfaceImpl2(String obj) {
        this.obj = obj;
    }

    @Override
    public String getValue() {
        return null;
    }
}
