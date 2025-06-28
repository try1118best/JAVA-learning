import java.io.*;

// 自定义HTTP响应类，负责向客户端发送HTTP响应
public class MyHttpResponse {

    // 输出流，用于向客户端发送响应数据
    private OutputStream outputStream;

    // 构造函数：初始化时必须提供输出流
    public MyHttpResponse(OutputStream outputStream) {
        // 保存用于响应客户端的输出流
        this.outputStream = outputStream;
    }

    // 发送静态资源的核心方法
    public void sendStaticResource(MyHttpRequest request) {
        // 缓冲区（实际未使用）
        byte[] bytes = new byte[1024];

        // 文件输入流，用于读取静态资源文件
        FileInputStream fileInputStream = null;

        // 获取请求的URI路径
        String filePath = request.getUri();

        // 处理根路径请求：将"/"重定向到index.html
        if (filePath.equals("/")) {
            filePath = "index.html";
        }

        // 构建文件对象：服务器根目录(WebContent) + 请求路径
        File file = new File(MyHttpServer.WebContent, filePath);

        // 检查请求的文件是否存在
        if (file.exists()) {
            // 文件存在时的处理逻辑
            String result = null;  // 存储文件内容字符串
            try {
                // 1. 打开文件流
                fileInputStream = new FileInputStream(file);

                // 2. 读取整个文件内容到字节数组
                byte[] fileByte = new byte[(int) file.length()];  // 创建与文件等大的数组
                fileInputStream.read(fileByte);  // 读取文件内容

                // 3. 关闭文件流
                fileInputStream.close();

                // 4. 将字节数据转换为字符串（注意：未指定编码）
                result = new String(fileByte);

                // 5. 包装HTTP响应并发送
                outputStream.write(warpMessage("200", result).getBytes());

            } catch (Exception e) {
                // 处理文件读取异常
                e.printStackTrace();
            }
        } else {
            // 文件不存在时的处理逻辑（404错误）
            try {
                // 创建404错误消息
                String errorMessage = "404 File Not Found! The requested URL " +
                        request.getUri() + " was not found on this server";

                // 包装为HTTP响应
                String result = warpMessage("404", errorMessage);

                // 发送错误响应
                outputStream.write(result.getBytes());

            } catch (IOException e) {
                // 处理输出异常
                e.printStackTrace();
            }
        }
    }

    // 包装HTTP响应消息的方法
    private String warpMessage(String status, String message) {
        // 构建HTTP响应头：
        // 1. HTTP版本和状态码（如HTTP/1.1 200）
        // 2. 内容类型（固定为text/html）
        // 3. 内容长度（message的长度）
        // 4. 空行（\r\n\r\n）分隔头和正文
        // 5. 响应正文（message）
        return "HTTP/1.1 " + status + "\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + message.length() + "\r\n" +
                "\r\n" +
                message;
    }
}