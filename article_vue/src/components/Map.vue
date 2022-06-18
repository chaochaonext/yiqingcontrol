<template>
    <div class="animated fadeIn">
        <div>数据来源自：https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=localCityNCOVDataList,diseaseh5Shelf</div>
        <div>最近更新于：{{this.lastDate}}</div>
        <!-- <Row> -->
            <div id="main"></div>
        <!-- </Row> -->
    </div>
</template>
<script>
    import echarts from 'echarts'
    import {postRequest,getRequest} from '@/utils/api'
    import 'echarts/map/js/china.js'
    import store from '../store/index'

    export default {
        data () {
            return {
                lastDate:'',
                QQData:{},
                provinces:[],
            }
        },
        store,
        methods: {
            //初始化地图
            buildMap(){
                let myChart = echarts.init(document.getElementById('main'));
                let option = {
                    tooltip: {
                        formatter:function(params,ticket, callback){
                            return params.seriesName+'<br />'+params.name+'：'+params.value
                        }//数据格式化
                    },
                    visualMap: {
                        min: 0,
                        max: 1500,
                        left: 'left',
                        top: 'bottom',
                        text: ['高','低'],//取值范围的文字
                        inRange: {
                            color: ['#fff4e6', '#dd2002']//取值范围的颜色
                        },
                        show:true//图注
                    },
                    geo: {
                        map: 'china',
                        roam: false,//不开启缩放和平移
                        zoom:1.23,//视角缩放比例
                        label: {
                            normal: {
                                show: true,
                                fontSize:'10',//注意：地图省份名字字体如果过大会导致字体重叠
                                color: 'rgba(0,0,0,0.7)'
                            }
                        },
                        itemStyle: {
                            normal:{
                                borderColor: 'rgba(0, 0, 0, 0.2)'
                            },
                            emphasis:{
                                areaColor: '#F3B329',//鼠标选择区域颜色
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                                shadowBlur: 20,
                                borderWidth: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    },
                    series : [
                        {
                            name: '现有病例',
                            type: 'map',
                            geoIndex: 0,
                            // 此处为展示数据的位置
                            data:this.provinces
                        }
                    ]
                };
                myChart.setOption(option);
                console.log("...")
            },
            //初始化数据
            initData(){
                postRequest("/sms/getQQData", 
                    this.user
                ).then((resp) => {
                    if (resp) {
                        if (resp.obj == false) {
                            Message.warning({ message: resp.msg });
                        } else {
                            console.log(resp)
                            this.QQData = resp.data;
                            this.lastDate = this.QQData.data.diseaseh5Shelf.lastUpdateTime;
                            var children = this.QQData.data.diseaseh5Shelf.areaTree[0].children;
                            for(var i = 0;i < children.length;i++){
                                var name = children[i].name;
                                var value = children[i].total.nowConfirm;
                                this.provinces.push({name,value})
                            }
                            console.log(this.provinces)
                            this.$store.dispatch("initQQData",this.QQData);
                            this.buildMap();
                        }
                    } else {
                        return false;
                    }
                });
            },
        },
        mounted() {
            this.initData();
            this.buildMap();
        },
    }
</script>
 
<style scoped>
    *{margin:0;padding:0}
    html,body{
        width:100%;
        height:100%;
    }
    #main{
        width:1080px;
        height:900px;
        margin: 0px auto;
        border:none;
    }
</style>