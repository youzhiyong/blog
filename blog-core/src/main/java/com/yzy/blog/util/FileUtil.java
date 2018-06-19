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
package com.yzy.blog.util;

import com.yzy.blog.business.enums.QiniuUploadType;
import com.yzy.blog.framework.exception.ZhydFileException;
import com.yzy.blog.plugin.QiniuApi;
import com.yzy.blog.business.enums.QiniuUploadType;
import com.yzy.blog.framework.exception.ZhydFileException;
import com.yzy.blog.plugin.QiniuApi;
import com.yzy.blog.plugin.UpyunAPI;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

/**
 * 文件操作工具类
 *
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @website https://www.youzhiyong.top
 * @date 2018/01/09 17:40
 * @since 1.0
 */
public class FileUtil {
    private static final String[] PICTURE_SUFFIXS = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};

    /**
     * 删除目录，返回删除的文件数
     *
     * @param rootPath
     *         待删除的目录
     * @param fileNum
     *         已删除的文件个数
     * @return 已删除的文件个数
     */
    public static int deleteFiles(String rootPath, int fileNum) {
        File file = new File(rootPath);
        if (!file.exists()) {
            return -1;
        }
        if (file.isDirectory()) {
            File[] sonFiles = file.listFiles();
            if (sonFiles != null && sonFiles.length > 0) {
                for (File sonFile : sonFiles) {
                    if (sonFile.isDirectory()) {
                        fileNum = deleteFiles(sonFile.getAbsolutePath(), fileNum);
                    } else {
                        sonFile.delete();
                        fileNum++;
                    }
                }
            }
        } else {
            file.delete();
        }
        return fileNum;
    }


    public static String getPrefix(File file) {
        return getPrefix(file.getName());
    }

    public static String getPrefix(String fileName) {
        int idx = fileName.lastIndexOf(".");
        int xie = fileName.lastIndexOf("/");
        idx = idx == -1 ? fileName.length() : idx;
        xie = xie == -1 ? 0 : xie + 1;
        return fileName.substring(xie, idx);
    }

    public static String getSuffix(File file) {
        return getSuffix(file.getName());
    }

    public static String getSuffix(String fileName) {
        int idx = fileName.lastIndexOf(".");
        idx = idx == -1 ? fileName.length() : idx;
        return fileName.substring(idx);
    }

    public static boolean isPicture(String suffix) {
        return !StringUtils.isEmpty(suffix) && Arrays.asList(PICTURE_SUFFIXS).contains(suffix.toLowerCase());
    }

    public static void mkdirs(String filePath) {
        File file = new File(filePath);
        mkdirs(file);
    }

    public static void mkdirs(File file) {
        if (!file.exists()) {
            if (file.isDirectory()) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs();
            }
        }
    }

    /**
     *
     * @param file
     * @param uploadType
     * @param isQiniu  true: 上传到七牛云    false: 上传到又拍云
     * @return
     */
    public static String upload(MultipartFile file, QiniuUploadType uploadType, boolean isQiniu) {
        // 不可为空并且file为空，抛出异常
        if (null == file) {
            throw new ZhydFileException("请选择图片");
        }
        try {
            String filePath = "";
            boolean isPicture = FileUtil.isPicture(FileUtil.getSuffix(file.getOriginalFilename()));
            if (isPicture) {
                if (isQiniu) {
                    //使用七牛云
                    filePath = QiniuApi.getInstance()
                            .withFileName(file.getOriginalFilename(), uploadType)
                            .upload(file.getBytes());
                    return UrlCodeUtil.encode(filePath);
                } else {
                    //使用又拍云
                    return UpyunAPI.getInstance().writeFile(file.getOriginalFilename(), file.getBytes());
                }

            } else {
                throw new ZhydFileException("只支持图片");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZhydFileException("上传图片发生异常", e);
        }
    }

    /**
     * 删除七牛上的文件
     *
     * @param key
     *         上传成功是返回的文件路径
     * @return
     */
    public static boolean removeQiniu(String key) {
        // 不可为空并且file为空，抛出异常
        if (StringUtils.isEmpty(key)) {
            throw new ZhydFileException("删除七牛文件失败:文件key为空");
        }
        try {
            return QiniuApi.getInstance().delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZhydFileException("删除七牛云文件发生异常", e);
        }
    }
}
