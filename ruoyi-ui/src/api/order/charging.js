import request from '@/utils/request'

// 查询充电订单列表
export function listCharging(query) {
  return request({
    url: '/order/charging/list',
    method: 'get',
    params: query
  })
}

// 查询充电订单详细
export function getCharging(sysId) {
  return request({
    url: '/order/charging/' + sysId,
    method: 'get'
  })
}

// 新增充电订单
export function addCharging(data) {
  return request({
    url: '/order/charging',
    method: 'post',
    data: data
  })
}

// 修改充电订单
export function updateCharging(data) {
  return request({
    url: '/order/charging',
    method: 'put',
    data: data
  })
}

// 删除充电订单
export function delCharging(sysId) {
  return request({
    url: '/order/charging/' + sysId,
    method: 'delete'
  })
}
