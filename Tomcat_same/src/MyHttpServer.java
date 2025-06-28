import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// 自定义HTTP服务器类
public class MyHttpServer {

    // 定义Web内容的根目录路径
    // 使用当前工作目录 + "WebContent"文件夹作为静态资源存放位置
    // File.separator 保证跨平台路径分隔符正确（Windows用\，Linux用/）
    public static String WebContent = System.getProperty("user.dir") + File.separator + "WebContent";

    // 服务器监听的端口号，默认8081
    private int port = 8081;

    // 服务器关闭标志，初始为false表示运行中
    private boolean isShutdown = false;

    // 主服务方法：监听端口并处理请求
    public void receiving() {
        // 服务器套接字，用于监听端口
        ServerSocket serverSocket = null;

        try {
            // 创建服务器套接字：
            // 参数1: 监听端口(8081)
            // 参数2: 请求队列长度(1)
            // 参数3: 绑定到本地回环地址(127.0.0.1)
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));

        } catch (IOException e) {
            // 处理服务器套接字创建异常（如端口被占用）
            e.printStackTrace();
        }

        // 服务器主循环：持续接收请求直到关闭标志为true
        while (!isShutdown) {
            // 客户端连接套接字
            Socket socket = null;

            // 输入流，用于读取客户端请求
            InputStream inputStream = null;

            // 输出流，用于向客户端发送响应
            OutputStream outputStream = null;

            try {
                // 阻塞等待客户端连接（当有浏览器访问时解除阻塞）
                socket = serverSocket.accept();

                // 获取客户端连接的输入流（接收请求数据）
                inputStream = socket.getInputStream();

                // 获取客户端连接的输出流（发送响应数据）
                outputStream = socket.getOutputStream();

                // 1. 创建HTTP请求对象并解析请求
                MyHttpRequest request = new MyHttpRequest(inputStream);
                request.parse();  // 解析请求获取URI

                // 2. 创建HTTP响应对象并发送静态资源
                MyHttpResponse response = new MyHttpResponse(outputStream);
                response.sendStaticResource(request);  // 发送请求的资源

            } catch (IOException e) {
                // 处理客户端连接或通信过程中的异常
                e.printStackTrace();
            } finally {
                // 关闭当前连接的socket,每个连接处理完后都会关闭，释放资源
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}