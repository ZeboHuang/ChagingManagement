import request from '@/utils/request'

// 查询充电站管理列表
export function listStation(query) {
  return request({
    url: '/charging/station/list',
    method: 'get',
    params: query
  })
}

// 查询充电站管理详细
export function getStation(sysId) {
  return request({
    url: '/charging/station/' + sysId,
    method: 'get'
  })
}

// 新增充电站管理
export function addStation(data) {
  return request({
    url: '/charging/station',
    method: 'post',
    data: data
  })
}

// 修改充电站管理
export function updateStation(data) {
  return request({
    url: '/charging/station',
    method: 'put',
    data: data
  })
}

// 删除充电站管理
export function delStation(sysId) {
  return request({
    url: '/charging/station/' + sysId,
    method: 'delete'
  })
}
