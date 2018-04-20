package com.example.pay.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 */
public class FTPUtil {
    private final static Logger logger = LoggerFactory.getLogger(FTPUtil.class);
    /**
     * @param hostname    FTP服务器地址
     * @param port        FTP服务器端口号
     * @param username    FTP登录帐号
     * @param password    FTP登录密码
     * @param pathname    FTP服务器保存目录
     * @param fileName    上传到FTP服务器后的文件名称
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String hostname, int port, String username, String password, String pathname, String fileName, InputStream inputStream) {
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        try {
            // 连接FTP服务器
            ftpClient.connect(hostname, port);
            // 登录FTP服务器
            ftpClient.login(username, password);
            // 是否成功登录FTP服务器
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                return flag;
            }
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            if (!ftpClient.changeWorkingDirectory(pathname)) {// 如果不能进入pathname下，说明此目录不存在！
                if (!ftpClient.makeDirectory(pathname)) {
                    logger.error("创建文件目录【" + pathname + "】 失败！");
                } else {
                    ftpClient.changeWorkingDirectory(pathname);// 进入目录
                    ftpClient.storeFile(fileName, inputStream);
                    inputStream.close();
                    ftpClient.logout();
                    flag = true;
                }
            } else {
                ftpClient.storeFile(fileName, inputStream);
                inputStream.close();
                ftpClient.logout();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath (注意是相对路径) FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public static Boolean downFtpFile(String url, int port, String username, String password, String remotePath, String fileName, String localPath) throws IOException {
        boolean downFileFlag = false;
        FTPClient ftp = new FTPClient();
        try {
            ftp.setControlEncoding("UTF-8");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
            conf.setServerLanguageCode("zh");
            int reply;
            ftp.connect(url, port);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  //二进制方式下载
            ftp.enterLocalPassiveMode();        //设置被动模式  防止linux下因为防火墙原因失败
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return downFileFlag;
            }
            if (!ftp.changeWorkingDirectory(remotePath)) {// 转移到FTP服务器目录
                downFileFlag = false;// 切换失败
                logger.error("切换到ftp服务器目录【" + remotePath + "】 失败！");
            } else {
                File localPathDir = new File(localPath);
                if (!localPathDir.exists()) {
                    localPathDir.mkdirs();
                }
                FTPFile[] fs = ftp.listFiles();
                for (FTPFile ff : fs) {
                    if (ff.getName().startsWith(fileName)) {
                        File localFile = new File(localPath + "/" + ff.getName());
                        OutputStream is = new FileOutputStream(localFile);
                        ftp.retrieveFile(ff.getName(), is);
                        is.close();
                        downFileFlag = true;
                    }
                }
                ftp.logout();
            }
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error("关闭ftp连接异常");
                }
            }
        }
        return downFileFlag;
    }
    public static void main(String[] args) {

        System.out.println("1");
    }
}
