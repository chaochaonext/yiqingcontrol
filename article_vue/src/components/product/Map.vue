<template>
    <div class="animated fadeIn" style="background-color: #66b2ff">
        <!-- <Row> -->
            <div id="main"></div>
        <!-- </Row> -->
    </div>
</template>
<script>
    import echarts from 'echarts'
    import {postRequest,getRequest} from '@/utils/api'
    import 'echarts/map/js/china.js'
    export default {
        data () {
            return {
                QQData:{

                },
                provinces:[
                    {name:"南海诸岛",value:1000}
                ]
            }
        },
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
                            name: '新增病例',
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
                            var provincesMap = new Map(Object.entries(this.QQData.provincesCompareMap))
                            for(var [key,values] of provincesMap){
                                var name = key;
                                var value = values.confirmAdd
                                console.log(name,value)
                                this.provinces.push({name,value})
                            }
                            console.log(this.provinces)
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