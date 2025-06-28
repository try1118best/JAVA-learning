// 测试类，用于启动HTTP服务器
public class Test {

    // Java程序的主入口方法
    public static void main(String[] args) {
        // 打印服务器启动信息
        System.out.println("Server startup successfully");

        // 创建HTTP服务器实例
        MyHttpServer server = new MyHttpServer();

        // 启动服务器，开始监听端口并处理请求
        server.receiving();
    }
}