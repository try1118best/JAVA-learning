import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//定义Socket服务，循环接收请求
//自定义HTTP服务器类
public class MyHttpServer {
    public static String WebContent = System.getProperty("user.dir") +
            File.separator + "WebContent";

    private int port = 8081;
    private boolean isShutdown = false;

    public void receiving() {
        //服务器套接字，用于监听端口
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //服务器主循环
        while (!isShutdown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                //创建请求
                MyHttpRequest request = new MyHttpRequest(inputStream);
                request.parse();
                //创建响应
                MyHttpResponse response = new MyHttpResponse(outputStream);
                response.sendStaticResource(request);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
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
