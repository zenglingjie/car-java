import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import User from './views/navuser/User.vue'
import Form from './views/navuser/Form.vue'
import List from './views/navuser/List.vue'
import FactorType from './views/navfactor/Type.vue'
import Operators from './views/navfactor/Operators.vue'
import FactorList from './views/navfactor/List.vue'
import Audit from './views/navaudit/Audit.vue'

let routes = [

    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    {
        path: '/home',
        component: Home,
        name: '用户管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/home/main', component: Main, name: '主页', hidden: true },
            { path: '/home/user', component: User, name: '用户' },
            { path: '/home/form', component: Form, name: 'Form' },
            { path: '/home/list', component: List, name: '列表' },
        ]
    },
    {
        path: '/home',
        component: Home,
        name: '因子管理',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/home/type', component: FactorType, name: '因子类型' },
            { path: '/home/operators', component: Operators, name: '操作类型' },
            { path: '/home/list', component: FactorList, name: '因子列表' }
        ]
    },
    {
        path: '/home',
        component: Home,
        name: '待审核',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            { path: '/home/audit', component: Audit, name: '待审核' }
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;