import request from '@/utils/request'

// 查询支付订单列表
export function listPayment(query) {
  return request({
    url: '/order/payment/list',
    method: 'get',
    params: query
  })
}

// 查询支付订单详细
export function getPayment(sysId) {
  return request({
    url: '/order/payment/' + sysId,
    method: 'get'
  })
}

// 新增支付订单
export function addPayment(data) {
  return request({
    url: '/order/payment',
    method: 'post',
    data: data
  })
}

// 修改支付订单
export function updatePayment(data) {
  return request({
    url: '/order/payment',
    method: 'put',
    data: data
  })
}

// 删除支付订单
export function delPayment(sysId) {
  return request({
    url: '/order/payment/' + sysId,
    method: 'delete'
  })
}
