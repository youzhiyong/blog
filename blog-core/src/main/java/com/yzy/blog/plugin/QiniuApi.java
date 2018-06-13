/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.yzy.blog.plugin;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yzy.blog.business.consts.CommonConst;
import com.yzy.blog.business.consts.DateConst;
import com.yzy.blog.business.enums.QiniuUploadType;
import com.yzy.blog.framework.holder.SpringContextHolder;
import com.yzy.blog.framework.property.AppProperties;
import com.yzy.blog.business.consts.CommonConst;
import com.yzy.blog.business.consts.DateConst;
import com.yzy.blog.business.enums.QiniuUploadType;
import com.yzy.blog.framework.property.AppProperties;
import com.yzy.blog.framework.holder.SpringContextHolder;
import com.yzy.blog.util.DateUtil;
import com.yzy.blog.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Qiniu云操作文件的api
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.youzhiyong.top
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class QiniuApi {
    private static final Logger LOG = LoggerFactory.getLogger(QiniuApi.class);
    private static final Object LOCK = new Object();
    private AppProperties config;
    private String key;
    private Auth auth;
    private UploadManager uploadManager;

    private QiniuApi() {
        this.config = SpringContextHolder.getBean(AppProperties.class);
        auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
        uploadManager = new UploadManager();
    }

    public static QiniuApi getInstance() {
        synchronized (LOCK) {
            return new QiniuApi();
        }
    }

    public QiniuApi withFileName(String key, QiniuUploadType type) {
        return withFileName(key, type.getPath());
    }

    public QiniuApi withFileName(String key, String path) {
        String suffix = FileUtil.getSuffix(key);
        // 不用时间戳命名文件，改为具体的直观的日期命名文件
        String fileName = DateUtil.date2Str(new Date(), DateConst.MILLISECOND);
        this.key = path + (fileName + suffix);
        return this;
    }

    public String getUpToken() {
        return this.auth.uploadToken(config.getQiniuBucketName(), this.key, 3600L, new StringMap().put("insertOnly", Integer.valueOf(1)));
    }

    public String upload(File fileByte) throws IOException {
        Response res = this.uploadManager.put(fileByte, this.key, getUpToken());
        upload(res);
        return key;
    }

    public String upload(byte[] byteArr) throws IOException {
        Response res = this.uploadManager.put(byteArr, this.key, getUpToken());
        upload(res);
        return key;
    }

    private String upload(Response res) throws IOException {
        try {
            int status = res.statusCode;
            if (status == CommonConst.DEFAULT_SUCCESS_CODE) {
                StringMap map = res.jsonToMap();
                return String.valueOf(map.get("key"));
            }
        } catch (QiniuException e) {
            Response r = e.response;
            LOG.error(r.toString(), e);
        }
        return null;
    }

    public boolean delete(String fileName) {
        BucketManager bucketManager = new BucketManager(this.auth);
        try {
            bucketManager.delete(config.getQiniuBucketName(), fileName);
            return true;
        } catch (QiniuException e) {
            Response r = e.response;
            LOG.error(r.toString(), e);
        }
        return false;
    }

    public FileInfo getFileInfo(String fileName) {
        BucketManager bucketManager = new BucketManager(this.auth);

        FileInfo info = null;
        try {
            info = bucketManager.stat(config.getQiniuBucketName(), fileName);
            LOG.info(info.hash);
            LOG.info(info.key);
        } catch (QiniuException e) {
            Response r = e.response;
            LOG.error(r.toString(), e);
        }
        return info;
    }

    public String download(String fileUrl) {
        return this.auth.privateDownloadUrl(fileUrl, 3600L);
    }

}
