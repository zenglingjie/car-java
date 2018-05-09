<template>
	<el-form ref="form" :model="form" label-width="80px" @submit.prevent="onSubmit" style="margin:20px;width:60%;min-width:600px;">
		<el-form-item label="名称">
			<el-input v-model="form.name"></el-input>
		</el-form-item>
		<el-form-item label="类型">
			<el-select v-model="form.region" placeholder="请选择类型">
				<el-option label="类型一" value="shanghai"></el-option>
				<el-option label="类型二" value="beijing"></el-option>
			</el-select>
		</el-form-item>
		<el-form-item label="时间">
			<el-col :span="11">
				<el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
			</el-col>
			<el-col class="line" :span="2">-</el-col>
			<el-col :span="11">
				<el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
			</el-col>
		</el-form-item>
		<el-form-item label="推送">
			<el-switch on-text="" off-text="" v-model="form.delivery"></el-switch>
		</el-form-item>
		<el-form-item label="性质">
			<el-checkbox-group v-model="form.type">
				<el-checkbox label="性质1" name="type"></el-checkbox>
				<el-checkbox label="性质2" name="type"></el-checkbox>
				<el-checkbox label="性质3" name="type"></el-checkbox>
				<el-checkbox label="性质4" name="type"></el-checkbox>
			</el-checkbox-group>
		</el-form-item>
		<el-form-item label="资源">
			<el-radio-group v-model="form.resource">
				<el-radio label="资源1"></el-radio>
				<el-radio label="资源2"></el-radio>
			</el-radio-group>
		</el-form-item>
		<el-form-item label="描述">
			<el-input type="textarea" v-model="form.desc"></el-input>
		</el-form-item>
		<el-form-item>
			<el-button type="primary">立即创建</el-button>
			<el-button @click.native.prevent>取消</el-button>
		</el-form-item>
	</el-form>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				}
			}
		},
		methods: {
			onSubmit() {
				console.log('submit!');
			}
		}
	}

</script>