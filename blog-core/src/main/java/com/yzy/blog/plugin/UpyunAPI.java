package com.yzy.blog.plugin;

import com.UpYun;
import com.yzy.blog.framework.holder.SpringContextHolder;
import com.yzy.blog.framework.property.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Description:
 * Date: 2018-06-19
 *
 * @author youzhiyong
 */
public class UpyunAPI {
    private static final Logger LOG = LoggerFactory.getLogger(UpyunAPI.class);
    private static final Object LOCK = new Object();
    private AppProperties config;
    private UpYun upyun;
    private static String IMG_PATH = "/img/";

    private UpyunAPI() {
        this.config = SpringContextHolder.getBean(AppProperties.class);
        upyun = new UpYun(config.getUpYunBucketName(), config.getUpYunUsername(), config.getUpYunPassword());
        //是否开启 debug 模式：默认不开启
        upyun.setDebug(config.getUpYunIsDebug());
        //手动设置超时时间：默认为30秒
        upyun.setTimeout(180);
        //选择最优的接入点
        upyun.setApiDomain(UpYun.ED_AUTO);
    }

    public static UpyunAPI getInstance() {
        synchronized (LOCK) {
            return new UpyunAPI();
        }
    }

    public boolean mkDir(String path) throws Exception {
        return upyun.mkDir(path, true);
    }

    public boolean rmDir(String path) throws Exception {
        return upyun.rmDir(path);
    }

    public List<UpYun.FolderItem> readDir(String path) throws Exception {
        List<UpYun.FolderItem> items = upyun.readDir(path);
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
        return items;
    }

    /**
     * 上传文件
     * @param fileName  文件名  + 后缀
     * @param datas
     * @return 文件存储路径  {baseUrl}/img/filename
     * baseUrl 的组成 {bucketName}.b0.upaiyun.com    如 abc.b0.upaiyun.com
     * 可通过配置cname   将images.youzhiyong.top  转发到  {bucketName}.b0.upaiyun.com
     * 所以文件访问路径可为
     * images.youzhiyong.top/path  或者  store-yzy.b0.upaiyun.com/path
     * @throws Exception
     */
    public String writeFile(String fileName, byte[] datas) throws Exception {
        String path = IMG_PATH + fileName;
        boolean result = upyun.writeFile(path, datas, true);
        if (result) {
            return path;
        }
        throw new Exception("上传失败！");
    }
}
