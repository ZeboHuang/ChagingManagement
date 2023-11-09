import request from '@/utils/request'

// 查询故障管理列表
export function listFault(query) {
  return request({
    url: '/charging/fault/list',
    method: 'get',
    params: query
  })
}

// 查询故障管理详细
export function getFault(faultId) {
  return request({
    url: '/charging/fault/' + faultId,
    method: 'get'
  })
}

// 新增故障管理
export function addFault(data) {
  return request({
    url: '/charging/fault',
    method: 'post',
    data: data
  })
}

// 修改故障管理
export function updateFault(data) {
  return request({
    url: '/charging/fault',
    method: 'put',
    data: data
  })
}

// 删除故障管理
export function delFault(faultId) {
  return request({
    url: '/charging/fault/' + faultId,
    method: 'delete'
  })
}
