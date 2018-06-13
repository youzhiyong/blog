<#include "include/macros.ftl">
<@header title="关于 | ${config.siteName}"
    keywords="${config.siteName},关于博客"
    description="一个程序员的个人博客,关于我的个人原创博客 - ${config.siteName}"
    canonical="/about">
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>关于
    </nav>
    <div class="row">
        <@blogHeader title="关于本站"></@blogHeader>
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <h5 class="legend-title">博主简介</h5>
                <div class="info">
                    <p>
                        博主从大四(2016.7)开始出来实习，2017年7月毕业后离开了武汉的实习单位来到深圳，至今已将近一年。现就职于某科技公司担任Java开发一职。
                    </p>
                    <blockquote style="overflow: hidden">
                        毕业一年，存款为零<br>
                        对酒当歌，人生几何
                        <br>
                    </blockquote>
                </div>
                <h5 class="legend-title">关于博客</h5>
                <div class="info">
                    <p>
                        网站也捣腾了一段时间了，说来也惭愧，从年初(2018.01)申请的半年的免费服务器，玩了几天就放下了。等到现在(2018.05)快过期的时候才又拿出来折腾，也算是将自己的博客上线了！同时也告诫自己，服务器可以续费，人生却不能，所以，骚年，行动起来吧！
                    </p>
                    <p>
                        目前，网站功能还在不断的的更新还完善中，网站内容也会不定期的进行更新。
                    </p>

                </div>
                <h5 class="legend-title">关于版权</h5>
                <div class="info">
                    本站大多数文章为站长原创，可能会有少部分文章系从互联网上转载过来的。一般能确认原创出处的，都会在文章中注明。但若因此造成侵权行为，烦请原作者<a target="_blank" href="mailto:yadong.zhang0415@gmail.com" title="点击给我发邮件" data-toggle="tooltip" data-placement="bottom" rel="external nofollow"><strong>告知</strong></a>，我会立刻删除相关内容。
                </div>
                <div class="alert alert-warning alert-dismissible simple-alert fade-in" role="alert">
                    <strong>注!</strong> 臭不要脸的把打赏按钮放在这里，只当做是一个功能吧，哈哈哈~~~我的支付宝账号：<span class="label label-success">13554241446</span>（#==@）！
                    扫码点击：<a class="btn btn-danger btn-sm reward" onclick="PaymentUtils.show();">打赏</a>
                </div>
            </div>
        </div>
        <#--<div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div id="comment-box" data-id="-3"></div>
            </div>
        </div>-->
    </div>
</div>

<@footer>
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>
</@footer>
