import request from '@/utils/request'

// 查询计费管理列表
export function listCharge_manage(query) {
  return request({
    url: '/charge_system/charge_manage/list',
    method: 'get',
    params: query
  })
}

// 查询计费管理详细
export function getCharge_manage(sysId) {
  return request({
    url: '/charge_system/charge_manage/' + sysId,
    method: 'get'
  })
}

// 新增计费管理
export function addCharge_manage(data) {
  return request({
    url: '/charge_system/charge_manage',
    method: 'post',
    data: data
  })
}

// 修改计费管理
export function updateCharge_manage(data) {
  return request({
    url: '/charge_system/charge_manage',
    method: 'put',
    data: data
  })
}

// 删除计费管理
export function delCharge_manage(sysId) {
  return request({
    url: '/charge_system/charge_manage/' + sysId,
    method: 'delete'
  })
}
