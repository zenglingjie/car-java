<template>
    <section>
        <div class="container">
            <div class="row">
                <div class="title col-xs-12"><span></span><a>热门</a></div>
                <div class="hot">
                    <div class="col-xs-4"><a>5月主力资金</a></div>
                    <div class="col-xs-4"><a>K线形态</a></div>
                    <div class="col-xs-4"><a>均线</a></div>
                    <div class="col-xs-4"><a>MACD</a></div>
                    <div class="col-xs-4"><a>龙虎榜</a></div>
                    <div class="col-xs-4"><a>BOLL</a></div>
                </div>
            </div>
            <div class="row">
                <div class="title col-xs-12"><span></span><a>市场</a></div>
                <div class="hot">
                    <div class="col-xs-4"><a>市场</a></div>
                    <div class="col-xs-4"><a>行业板块</a></div>
                    <div class="col-xs-4"><a>概念板块</a></div>
                </div>
            </div>
            <div class="row">
                <div class="title col-xs-12"><span></span><a>成长</a></div>
                <div class="hot">
                    <div class="col-xs-4"><a>净利润同比</a></div>
                    <div class="col-xs-4"><a>基本每股收益同比</a></div>
                    <div class="col-xs-4"><a>稀释每股收益同比</a></div>
                    <div class="col-xs-4"><a>营业收入同比</a></div>
                    <div class="col-xs-4"><a>营业利润同比</a></div>
                    <div class="col-xs-4"><a>利润总额同比</a></div>
                </div>
            </div>
            <div class="row footer">
                <div class="col-xs-9">已选择<span>1</span>种指标</div>
                <div class="col-xs-3"><a>查看结果</a></div>
            </div>
        </div>
    </section>
</template>

<script>
    import { requestLogin } from '../api/api';
    import http from '../common/js/http'
    export default {
        data() {
            return {

            };
        },
        methods: {
        },
    }
</script>

<style lang="css" scoped>
    /*body:hover{background: #ff00ff;}*/
    a:hover{background: #f7ba2a}
    .row{margin: auto;}
    .title{padding: 4% 0; width: 100%; overflow: hidden;line-height: 0.8rem; vertical-align: middle;}
    .title span{display:block; float:left;width: 0.1rem;height:0.4rem;border-radius: 5px;background: #F04141; margin-top: 0.2rem;}
    .title a{display:block; float: left; font-size: 0.56rem;color: #000000; line-height: 0.8rem; padding-left: 6px;}
    .hot{overflow: hidden; width: 100%}
    .hot>div{overflow: hidden}
    .hot>div a:hover{background: #00ff00} a{display: block;background: #F0F0F0 100%; border-radius: 5px; height: 1.6rem; line-height: 1.6rem; margin: 0 0.1rem 0.2rem 0.1rem; padding: 0 8px; text-align: center; border: 1px solid #F0F0F0; color: #555555; font-size: 0.48rem;}
    .footer{overflow: hidden; position: fixed; z-index: 10; width: 100%; height: 2.4rem; bottom: 0; left: 0; padding: 0 0.4rem; background: #ffffff;}
    .footer>div{font-size: 0.56rem; line-height: 2.4rem; vertical-align: middle;}
    .footer>div span{color: #F04141;}
    .footer>div a{width: 4rem; height: 1.6rem; background: rgba(240,240,240,1); border-radius: 5px; display: block; line-height: 1.6rem; text-align:center; margin-top: 0.4rem;}
    /*.footer>div a:hover{background: #F04141; color: #ffffff;}*/

</style>