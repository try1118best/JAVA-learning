import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

//自定义Http响应类
public class MyHttpResponse {
    private OutputStream outputStream;

    public MyHttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    // 发送静态资源的核心方法
    public void sendStaticResource(MyHttpRequest request) {
        byte[] bytes = new byte[1024];
        FileInputStream fileInputStream = null;
        String filePath = request.getUri();
        if(filePath.equals("/")){
            filePath = "index.html";
        }
        File file = new File(MyHttpServer.WebContent,filePath);
        if(file.exists()){
            String result = null;
            try {
                fileInputStream = new FileInputStream(file);
                byte[] fileByte = new byte[(int) file.length()];
                fileInputStream.read(fileByte);
                fileInputStream.close();
                result = new String(fileByte, "UTF-8");
                outputStream.write(warpMessage("200",result).getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                String errorMessage = "404 File Not Found! The requested URL " +
                        request.getUri() + " was not found on this server";
                // 包装为HTTP响应
                String result = warpMessage("404", errorMessage);
                // 发送错误响应
                outputStream.write(result.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String warpMessage(String status, String message){
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
