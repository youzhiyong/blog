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
package com.yzy.blog.framework.tag;

import com.yzy.blog.business.service.*;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 自定义的freemarker标签
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.youzhiyong.top
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
public class CustomTagDirective implements TemplateDirectiveModel {
    private static final String METHOD_KEY = "method";
    @Autowired
    private BizTypeService bizTypeService;
    @Autowired
    private BizCommentService commentService;
    @Autowired
    private BizTagsService bizTagsService;
    @Autowired
    private SysResourcesService resourcesService;
    @Autowired
    private SysConfigService configService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        if (map.containsKey(METHOD_KEY)) {
            String method = map.get(METHOD_KEY).toString();
            int pageSize = 10;
            if (map.containsKey("pageSize")) {
                String pageSizeStr = map.get("pageSize").toString();
                pageSize = Integer.parseInt(pageSizeStr);
            }
            switch (method) {
                case "types":
                    environment.setVariable("types", builder.build().wrap(bizTypeService.listAll()));
                    break;
                case "tagsList":
                    // 所有标签
                    environment.setVariable("tagsList", builder.build().wrap(bizTagsService.listAll()));
                    break;
                case "parentResources":
                    // 所有父级资源
                    environment.setVariable("parentResources", builder.build().wrap(resourcesService.listAllParentResource()));
                    break;
                case "recentComments":
                    // 近期评论
                    environment.setVariable("recentComments", builder.build().wrap(commentService.listRecentComment(pageSize)));
                    break;
                case "siteInfo":
                    // 站点属性
                    environment.setVariable("siteInfo", builder.build().wrap(configService.getSiteInfo()));
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }
}
