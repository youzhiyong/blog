<#-- 公共顶部 -->
<#macro header title="youzhiyong博客" keywords="默认文字" description="默认文字" canonical="">
<#include "/common/annotation.ftl">
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>${title}</title>
    <meta name="author" content="${config.authorName}(${config.authorEmail})">
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}" id="meta_description">
    <link rel="canonical" href="${config.siteUrl}${canonical}" />
    <#include "/layout/quote.ftl">
    <#nested>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?baf5d51a5ac535126f1f6a9c1a69bca6";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>
<body>
    <#include "/layout/header.ftl"/>
</#macro>

<#-- 公共底部 -->
<#macro footer>
    <#include "/layout/footer.ftl"/>

    <#nested>

    </body>
</html>
</#macro>

<#-- 分页组件 -->
<#macro pageBar>
    <#if page?exists && (page.pages > 1)>
    <nav>
        <ul class="pager page-btn" data-url="${config.siteUrl}/${url?if_exists}" data-search="${(model.keywords == null || model.keywords == '')?string('false', 'true')}">
            <#if page.hasPreviousPage>
            <li><a class="pointer" data-page="${page.prePage}"><i class="fa fa-angle-double-left"></i></a></li>
            </#if>
            <#list 1..page.pages as item>
            <li><a ${(page.pageNum == item)?string('class="pointer active"','class="pointer" data-page="${item?c}"')}>${item?c}</a></li>
            </#list>
            <#if page.hasNextPage>
            <li><a class="pointer" data-page="${page.nextPage}"><i class="fa fa-angle-double-right"></i></a></li>
            </#if>
        </ul>
    </nav>
    </#if>
</#macro>


<#-- blog-header -->
<#macro blogHeader title="Header">
    <div class="col-sm-12 blog-main">
        <div class="blog-header">
            <h1 class="blog-title">${title}</h1>
            <p class="blog-description" id="hitokoto"></p>
            <div class="info">
                <a href="javascript:void(0);" target="_blank" title="点击QQ联系我"onclick="window.open('tencent://message/?uin=847371174&amp;Site=www.${config.domain}&amp;Menu=yes')" rel="external nofollow"><i class="fa fa fa-qq fa-fw"></i>QQ联系</a>
                |
                <a href="mailto:847371174@qq.com" target="_blank" title="点击给我发邮件" rel="external nofollow"><i class="fa fa fa-envelope fa-fw"></i>邮箱联系</a>
                |
                <a href="https://www.jianshu.com/u/e3f496038c81" target="_blank" title="点击查看我的简书" rel="external nofollow"><i class="fa fa fa-weibo fa-fw"></i>__youzhiyong</a>
            </div>
        </div>
    </div>
</#macro>