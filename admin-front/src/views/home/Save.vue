
<template>
    <div class="form-horizontal">
      <div class="form-group">
        <div class="col-md-6">
          <label for="date">日期</label>
          <input type="date" id="date" class="form-control"  placeholder="请选择日期" v-model="date">
        </div>
        <div class="col-md-6">
          <label for="time">时间</label>
          <a >aaaaaa</a>
          <input type="number" id="time" class="form-control"  placeholder="请选择时间" v-model.number="timer">
        </div>
      </div>
      <div class="form-group" >
        <div class="col-md-12">
          <textarea  class="form-control" v-model="comment" placeholder="请输入信息"></textarea >
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-12">
          <button class="btn btn-primary" @click="save()">保存</button>
        </div>
      </div>
      <hr>

    </div>
</template>
<script>
    import {mapActions} from 'vuex';
    import $ from 'jquery'
    export default {
        data(){
            return {
                date:'',
                timer:'',
                comment:''
            }
        },
        components: {},
        methods: {
          ...mapActions([
            'saveplain',
            'incrementTotalTime'
          ]),
          save(){
            $('#time').val(5);
            let plain = {
                date:this.date,
                timer:this.timer,
                comment:this.comment
            };
            //this.$router.push('/time');
          }
        }
    }
</script>
<style lang="scss" scoped>
  a:hover{background: #f7ba2a}
</style>
