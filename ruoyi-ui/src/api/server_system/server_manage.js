import request from '@/utils/request'

// 查询运营商管理列表
export function listServer_manage(query) {
  return request({
    url: '/server_system/server_manage/list',
    method: 'get',
    params: query
  })
}

// 查询运营商管理详细
export function getServer_manage(id) {
  return request({
    url: '/server_system/server_manage/' + id,
    method: 'get'
  })
}

// 新增运营商管理
export function addServer_manage(data) {
  return request({
    url: '/server_system/server_manage',
    method: 'post',
    data: data
  })
}

// 修改运营商管理
export function updateServer_manage(data) {
  return request({
    url: '/server_system/server_manage',
    method: 'put',
    data: data
  })
}

// 删除运营商管理
export function delServer_manage(id) {
  return request({
    url: '/server_system/server_manage/' + id,
    method: 'delete'
  })
}
