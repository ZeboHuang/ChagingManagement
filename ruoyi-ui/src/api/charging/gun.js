import request from '@/utils/request'

// 查询充电枪列表
export function listGun(query) {
  return request({
    url: '/charging/gun/list',
    method: 'get',
    params: query
  })
}

// 查询充电枪详细
export function getGun(id) {
  return request({
    url: '/charging/gun/' + id,
    method: 'get'
  })
}

// 新增充电枪
export function addGun(data) {
  return request({
    url: '/charging/gun',
    method: 'post',
    data: data
  })
}

// 修改充电枪
export function updateGun(data) {
  return request({
    url: '/charging/gun',
    method: 'put',
    data: data
  })
}

// 删除充电枪
export function delGun(id) {
  return request({
    url: '/charging/gun/' + id,
    method: 'delete'
  })
}
