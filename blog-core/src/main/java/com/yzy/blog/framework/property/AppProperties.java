/**
 * MIT License
 *
 * Copyright (c) 2018 yadong.zhang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.yzy.blog.framework.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.youzhiyong.top
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    public Boolean enableKaptcha;

    public boolean getEnableKaptcha() {
        return null == enableKaptcha ? false : enableKaptcha;
    }

    public Map<String, String> qiniu = new HashMap<>();

    public String getQiniuAccessKey() {
        return this.qiniu.get("accessKey");
    }

    public String getQiniuSecretKey() {
        return this.qiniu.get("secretKey");
    }

    public String getQiniuBucketName() {
        return this.qiniu.get("bucketName");
    }

    public Map<String, String> upyun = new HashMap<>();

    public String getUpYunUsername() {
        return this.upyun.get("username");
    }

    public String getUpYunPassword() {
        return this.upyun.get("password");
    }

    public String getUpYunBucketName() {
        return this.upyun.get("bucketName");
    }

    public boolean getUpYunIsDebug() {
        return Boolean.valueOf(this.upyun.get("bucketName"));
    }
}
