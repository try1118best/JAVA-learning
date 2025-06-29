public class Test {
    public static void main(String[] args) {
        // 打印服务器启动信息
        System.out.println("Server startup successfully");
        MyHttpServer server = new MyHttpServer();
        server.receiving();
    }
}
