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
package com.yzy.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yzy.blog.persistence.beans.SysLink;
import com.yzy.blog.business.enums.LinkSourceEnum;
import com.yzy.blog.persistence.beans.SysLink;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.youzhiyong.top
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Link {
    private SysLink sysLink;

    public Link() {
        this.sysLink = new SysLink();
    }

    public Link(SysLink sysLink) {
        this.sysLink = sysLink;
    }

    @JsonIgnore
    public SysLink getSysLink() {
        return this.sysLink;
    }

    public Long getId() {
        return this.sysLink.getId();
    }

    public void setId(Long id) {
        this.sysLink.setId(id);
    }

    @NotEmpty(message = "站点地址不能为空")
    @Pattern(regexp = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]", message = "不合法的地址")
    public String getUrl() {
        return this.sysLink.getUrl();
    }

    public void setUrl(String url) {
        this.sysLink.setUrl(url);
    }

    @NotEmpty(message = "站点名称不能为空")
    @Length(max = 15, min = 1, message = "站点名称长度建议保持在15个字符以内")
    public String getName() {
        return this.sysLink.getName();
    }

    public void setName(String name) {
        this.sysLink.setName(name);
    }

    @NotEmpty(message = "站点描述不能为空")
    @Length(max = 30, min = 1, message = "站点描述这么长，亲你是想参加作文比赛么？^_^")
    public String getDescription() {
        return this.sysLink.getDescription();
    }

    public void setDescription(String description) {
        this.sysLink.setDescription(description);
    }

    public String getEmail() {
        return this.sysLink.getEmail();
    }

    public void setEmail(String email) {
        this.sysLink.setEmail(email);
    }

    public String getQq() {
        return this.sysLink.getQq();
    }

    public void setQq(String qq) {
        this.sysLink.setQq(qq);
    }

    public String getFavicon() {
        return this.sysLink.getFavicon();
    }

    public void setFavicon(String favicon) {
        this.sysLink.setFavicon(favicon);
    }

    public Boolean isStatus() {
        Boolean value = this.sysLink.getStatus();
        return value != null ? value : false;
    }

    public void setStatus(Boolean status) {
        this.sysLink.setStatus(status);
    }

    @JsonIgnore
    public LinkSourceEnum getSourceEnum() {
        if (StringUtils.isEmpty(getSource())) {
            return LinkSourceEnum.OTHER;
        }
        return LinkSourceEnum.valueOf(getSource());
    }

    public String getSource() {
        return this.sysLink.getSource();
    }

    public void setSource(LinkSourceEnum source) {
        if (null == source) {
            source = LinkSourceEnum.ADMIN;
        }
        this.sysLink.setSource(source.toString());
    }

    public void setSource(String source) {
        this.sysLink.setSource(source);
    }

    public Boolean isHomePageDisplay() {
        Boolean value = this.sysLink.getHomePageDisplay();
        return value != null ? value : false;
    }

    public void setHomePageDisplay(Boolean homePageDisplay) {
        this.sysLink.setHomePageDisplay(homePageDisplay);
    }


    public String getRemark() {
        return this.sysLink.getRemark();
    }

    public void setRemark(String remark) {
        this.sysLink.setRemark(remark);
    }

    public Date getCreateTime() {
        return this.sysLink.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysLink.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.sysLink.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysLink.setUpdateTime(updateTime);
    }

}
