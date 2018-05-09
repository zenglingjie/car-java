const apiMethods = {
  methods: {
    apiGet(url, data) {
      return new Promise((resolve, reject) => {
        axios.get(url, data).then((response) => {
          resolve(response.data)
        }, (response) => {
          reject(response)
          _g.closeGlobalLoading()
          bus.$message({
            message: '请求超时，请检查网络',
            type: 'warning'
          })
        })
      })
    },
    apiPost(url, data) {
      return new Promise((resolve, reject) => {
        axios.post(url, data).then((response) => {
          resolve(response.data)
        }).catch((response) => {
          console.log('f', response)
          resolve(response)
          bus.$message({
            message: '请求超时，请检查网络',
            type: 'warning'
          })
        })
      })
    },
    apiDelete(url, id) {
      return new Promise((resolve, reject) => {
        axios.delete(url + id).then((response) => {
          resolve(response.data)
        }, (response) => {
          reject(response)
          _g.closeGlobalLoading()
          bus.$message({
            message: '请求超时，请检查网络',
            type: 'warning'
          })
        })
      })
    },
    apiPut(url, id, obj) {
      return new Promise((resolve, reject) => {
        axios.put(url + id, obj).then((response) => {
          resolve(response.data)
        }, (response) => {
          _g.closeGlobalLoading()
          bus.$message({
            message: '请求超时，请检查网络',
            type: 'warning'
          })
          reject(response)
        })
      })
    },
    handelResponse(res, cb, errCb) {
      _g.closeGlobalLoading()
      if (res.code == 200) {
        cb(res.data)
      } else {
        if (typeof errCb == 'function') {
          errCb()
        }
        this.handleError(res)
      }
    },
    handleError(res) {
      if (res.code) {
        switch (res.code) {
          case 101:
            console.log('cookie = ', Cookies.get('rememberPwd'))
            if (Cookies.get('rememberPwd')) {
              let data = {
                rememberKey: Lockr.get('rememberKey')
              }
              this.reAjax('admin/relogin', data).then((res) => {
                this.handelResponse(res, (data) => {
                  this.resetCommonData(data)
                })
              })
            } else {
              _g.toastMsg('error', res.error)
              setTimeout(() => {
                router.replace('/')
              }, 1500)
            }
            break
          case 103:
            _g.toastMsg('error', res.error)
            setTimeout(() => {
              router.replace('/')
            }, 1500)
            break
          // case 400:
          //   this.goback()
          //   break
          default :
            _g.toastMsg('error', res.error)
        }
      } else {
        console.log('default error')
      }
    },
    resetCommonData(data) {
      console.log(data)
    },
    reAjax(url, data) {
      return new Promise((resolve, reject) => {
        axios.post(url, data).then((response) => {
          resolve(response.data)
        }, (response) => {
          reject(response)
          bus.$message({
            message: '请求超时，请检查网络',
            type: 'warning'
          })
        })
      })
    }
  },
  computed: {
    showLoading() {
      return store.state.globalLoading
    }
  }
}

export default apiMethods
