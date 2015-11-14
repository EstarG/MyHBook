pageConfig.FN_InitSlider("slide",pageConfig.DATA_MSlide);
pageConfig.TPL_MSlide={
    itemsWrap:"<ul class=\"slide-items\">{innerHTML}</ul>",
    itemsContent:"{for item in json}\
        <li data-tag=\"${item.aid}\">\
            {var v=item.data}\
            <a href=\"${v.href}\" target=\"_blank\" title=\"${v.alt}\">\
                <img height=\"${v.height}\" width=\"${v.width}\" src=\"${v.src}\" data-img=\"2\" />\
            </a>\
        </li>\
    {/for}",
    controlsWrap:"<div class=\"slide-controls\">{innerHTML}</div>",
    controlsContent:"{for item in json}\
        <span class=\"{if parseInt(item_index)==0}curr{/if}\">${parseInt(item_index)+1}</span>\
    {/for}"
};

jQuery("#slide").Jslider({
    data:pageConfig.DATA_MSlide,
    auto:true,
    reInit:true,
    slideWidth:(screen.width>=1210)?770:550,
    slideHeight:190,
    maxAmount:8,
    slideDirection:2,
    template:pageConfig.TPL_MSlide
},function(object){
    pageConfig.FN_ImgError(object.get(0));
});

$("#sortlist .item").unbind("mouseover").unbind("mouseout");
var sortlist = $("#sortlist .item");
$.each(sortlist,function(i, element){
    if ( !! $("h3", element).find("b").get(0)){
        $(element).hoverForIE6({
            delay: 150
        });
    }
});

/*一种注目*/
var hoverDelay;
$("#focus li").bind("mouseover",function(){
    if(! $(this).attr("data-info")){
        return;
    };
    var data=eval("(" + $(this).attr("data-info") + ")"),
        _this=$(this);
    hoverDelay = setTimeout(function(){
        _this.parent().parent().find(".spec-info").html("<div class=\"p-img\"><a href=\"" +data.url+ "\" target=\"_blank\"><img src=\"" + data.image + "\" width=\"130\" height=\"130\"></a></div>\
            <div class=\"p-name\"><a href=\"" +data.url+ "\" target=\"_blank\" title=\"\">" + data.name + "</a></div><div class=\"p-market\">" + data.market + "</div>\<div class=\"p-price\">" + data.price + "</div>\
            <div class=\"p-info\">" + data.info + "</div>");
        _this.addClass("current").siblings().removeClass();
    },300);
}).bind("mouseout",function(){
    clearTimeout(hoverDelay)
});

$("*[data-widget=tabs]").each(function(){
    $(this).Jtab({compatible:true})
});

$("#group .mc").imgScroll({
    visible: 1,
    speed: 244,
    step: 1,
    direction: 'x',
    loop: false,
    evtType: 'click',
    next: '#next',
    prev: '#prev',
    disableClass: 'disabled'
}, function(n, total, objs, exObjs) {
    objs.find('img').each(function() {
        if ( $(this).attr('data-src') ) {
            $(this).attr('src', $(this).attr('data-src'));
        }
    }); 
});

$("#newbook-top").each(function(){
    $(this).find("li").mouseover(function(){
        $(this).siblings().removeClass("curr").end().addClass("curr");
        $(this).find("img").each(function(){
            var src=$(this).attr("src2");
            if (src){
                $(this).removeAttr("src2").attr("src",src);
            }
        })
    })
});
$(".hot-total").each(function(){
    $(this).find("li").mouseover(function(){
        $(this).siblings().removeClass("curr").end().addClass("curr");
        $(this).find("img").each(function(){
            var src=$(this).attr("src2");
            if (src){
                $(this).removeAttr("src2").attr("src",src);
            }
        })
    })
});
$("#featurebook-top").each(function(){
    $(this).find("li").mouseover(function(){
        $(this).siblings().removeClass("curr").end().addClass("curr");
        $(this).find("img").each(function(){
            var src=$(this).attr("src2");
            if (src){
                $(this).removeAttr("src2").attr("src",src);
            }
        })
    })
});

var hoverDelay2;
$("#por-recommend .spec-list li").bind("mouseover",function(){
    if(! $(this).attr("data-info")){
        return;
    };
    var data=eval("(" + $(this).attr("data-info") + ")"),
        _this=$(this);
    hoverDelay2 = setTimeout(function(){
        _this.parent().parent().find(".spec-info").html("<div class=\"p-img\"><a href=\"" +data.url+"\" target=\"_blank\"><img src=\"" + data.image + "\" width=\"130\" height=\"130\" /></a><div class=\"author-name\">" + data.authorname + "</div></div>\
<div class=\"p-name\"><a href=\"" +data.url+"\" target=\"_blank\" title=\"\">" + data.name + "</a></div><div class=\"p-market\">" + data.market +"</div>\
<div class=\"p-price\">" + data.price + "</div>\
<div class=\"p-info\">" + data.info + "</div>");
    _this.addClass("current").siblings().removeClass("current");
},300);
}).bind("mouseout",function(){
    clearTimeout(hoverDelay2)
});

/*文字上滚*/
function setMarquee(id, childId1, childId2){
    
    var speed=40,
        con = document.getElementById(id),
        con1 = document.getElementById(childId1),
        con2 = document.getElementById(childId2);
    con2.innerHTML = con1.innerHTML;
    function marquee1(){
        if(con2.offsetHeight-con.scrollTop<=0)
            con.scrollTop-=con1.offsetHeight
        else{
            con.scrollTop++
        }
    };
    var mar=setInterval(marquee1,speed);
    con.onmouseover=function() {clearInterval(mar)};
    con.onmouseout=function() {mar=setInterval(marquee1,speed)};
};
setMarquee('marquee','marqueeCon1','marqueeCon2');
setMarquee('f_marquee','f_marqueeCon1','f_marqueeCon2');

//馆
pageConfig.DATA_Guan_Tabs={
    "sheyingguan":{
        "name":"摄影馆",
        "url":"http://jmall.jd.com/p19458.html",
        "def":"911",
        "classes":{
            "911":"理论/评论",
            "912":"技法/教程",
            "913":"器材/宝典",
            "914":"作品/后期"
        }
    },
    "jisuanjiguan":{
        "name":"计算机馆",
        "url":"http://www.jd.com/products/1713-3287-000.html",
        "def":"915",
        "classes":{
            "915":"程序设计",
            "916":"数码/辅助设计",
            "917":"办公软件",
            "918":"网络技术"
        }
    },
    "kaoshiguan":{
        "name":"考试馆",
        "url":"http://channel.jd.com/1713-3290.html",
        "def":"919",
        "classes":{
            "919":"考研",
            "920":"外语考试",
            "921":"执业/职业考试",
            "922":"公务员考试"
        }
    },
     "caijingguan":{
        "name":"财经馆",
        "url":"http://channel.jd.com/1713-3266.html",
        "def":"2637",
        "classes":{
            "2637":"商业",
            "2638":"经济/金融",
            "2639":"理财/投资",
            "2640":"股票"
        }
    },
    "qingchundongmanguan":{
        "name":"青春动漫馆",
        "url":"http://jmall.jd.com/p113876.html",
        "def":"2641",
        "classes":{
            "2641":"爱情/情感",
            "2642":"动漫",
            "2643":"绘本",
            "2644":"玄幻/悬疑"
        }
    },
    "shaoerguan":{
        "name":"少儿馆",
        "url":"http://www.jd.com/products/1713-3263-000.html",
        "def":"906",
        "classes":{
            "906":"0-2岁",
            "907":"3-6岁",
            "908":"7-10岁",
            "909":"11-14岁"
        }
    }
};

pageConfig.TPL_Guan_Tabs="<ul class='tab'>\
{var def=def}\
{for item in classes}\
    {if item_index==def}\
        <li class='curr' data-widget='tab-item'>\
    {else}\
        <li data-widget='tab-item' data-tag='${item_index}'>\
    {/if}\
        ${classes[item_index]}</li>\
    {/for}\
</ul>\
<div class='clr'></div>";

pageConfig.TPL_Guan_Contents="\
{for item in data}\
    {if item_index==906||item_index==911||item_index==915||item_index==919||item_index==2637||item_index==2641}\
    <div data-widget='tab-content' class='tabcon'>\
    {else}\
    <div data-widget='tab-content' class='tabcon hide'>\
    {/if}\
            <ul>\
            {for sitem in item}\
                {if sitem_index<5}\
                <li>\
                    <div class='p-img bookimg'>\
                        {var domain=pageConfig.FN_GetImageDomain(sitem.a)}\
                        <div class='i-img'><a href='http://book.jd.com/${sitem.a}.html' target='_blank'>\
                            <img width='130' height='130' \
                             {if item_index==906||item_index==911||item_index==915||item_index==919||item_index==2637||item_index==2641}\
                            src\
                            {else}\
                            data-src\
                            {/if}\
                            ='${domain}n3/${sitem.e}'>\
                        </a></div>\
                    </div>\
                    <div class='p-name'><a href='http://book.jd.com/${sitem.a}.html' target='_blank'>${sitem.b}</a></div>\
                    <div class='p-market'><del>￥${sitem.d}</del></div>\
                    <div class='p-price'><strong>￥${sitem.c}</strong></div>\
                </li>\
                {else}\
                </ul>\
                <div class='popular-tags side-m'>\
                    <div class='smt'><h3>热门标签</h3></div>\
                    <div class='smc'>\
                        {for pitem in sitem}\
                        <a href='${pitem.Href}' target='_blank'><span>${pitem.Tag}</span></a>\
                        {/for}\
                    </div>\
                </div>\
                {/if}\
            {/for}\
    </div>\
{/for}";


$("#children .mt dt").click(function(){
    $("#children .mt dd").fadeIn();
});
$("#children .mt div[data-tag]").click(function(){
    var tag = $(this).attr("data-tag"),
        guan=pageConfig.DATA_Guan_Tabs[tag];
    $("#children .mt h2").html(guan.name);
    $("#children .mt .extra a").attr("href",guan.url);

    $(this).css("font-weight","bold").siblings().css("font-weight","normal").end().parent().fadeOut();
    var dataGuan,
        tabsText,
        contentText,
        obj={};
    dataGuan=pageConfig.DATA_Guan[tag];
    obj.data=dataGuan;
    tabsText=pageConfig.TPL_Guan_Tabs.process(guan)
    contentText=pageConfig.TPL_Guan_Contents.process(obj);
    $("#children .mc").html(tabsText+contentText);
    $("#children").Jtab({compatible:true},function(source,object,n){
        object.find("img").Jlazyload({
            type:"image",
            source:"data-src"
        },function(){
            pageConfig.FN_ImgError(object.get(0));
        })
    });
});
$("#group,#newbooks,#sort-recommend,#jd-study,#portraits,#special-books").each(function(){
    $(this).Jtab({compatible:true},function(source,object,n){
        object.find("img").Jlazyload({
            type:"image",
            source:"data-src"
        },function(){
            pageConfig.FN_ImgError(object.get(0));
        })
    });
});