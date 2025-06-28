import java.io.IOException;
import java.io.InputStream;

// 自定义HTTP请求类，负责解析客户端发送的HTTP请求
public class MyHttpRequest {

    // 输入流，用于读取客户端发送的请求数据
    private InputStream inputStream;

    // 存储解析出的请求URI（资源路径）
    private String uri;

    // 构造函数：初始化时必须提供输入流
    public MyHttpRequest(InputStream inputStream) {
        // 将传入的输入流保存到成员变量
        this.inputStream = inputStream;
    }

    // 解析HTTP请求的核心方法
    public void parse() {
        // 创建字符串缓冲区，用于存储请求数据（初始容量2048字符）
        StringBuffer requestStr = new StringBuffer(2048);

        // 实际读取的字节数
        int i = 0;

        // 字节缓冲区（2KB大小）
        byte[] buffer = new byte[2048];

        try {
            // 从输入流读取数据到buffer，返回实际读取字节数
            i = inputStream.read(buffer);
        } catch (IOException e) {
            // 处理读取异常（仅打印堆栈跟踪）
            e.printStackTrace();
        }

        // 将读取的字节数据转换为字符并拼接到字符串缓冲区
        for (int j = 0; j < i; j++) {
            // 将字节强制转换为字符（注意：可能有编码问题）
            requestStr.append((char) buffer[j]);
        }

        // 调试用：打印完整的请求字符串（已注释）
        // System.out.println(requestStr.toString());

        // 从请求字符串中解析出URI
        uri = parseUri(requestStr.toString());
    }

    // 获取解析后的URI
    public String getUri() {
        return uri;
    }

    // 从HTTP请求行中提取URI的方法
    private String parseUri(String requestStr) {
        // 定义两个空格的位置索引
        int index1, index2;

        // 查找第一个空格的位置（请求方法后的空格）
        index1 = requestStr.indexOf(' ');

        // 查找第二个空格的位置（URI后的空格）
        index2 = requestStr.indexOf(' ', index1 + 1);

        // 截取两个空格之间的字符串（即URI）
        return requestStr.substring(index1 + 1, index2);
    }
}