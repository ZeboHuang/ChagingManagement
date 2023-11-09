import request from '@/utils/request'

// 查询周报表列表
export function listWeek(query) {
  return request({
    url: '/report/week/list',
    method: 'get',
    params: query
  })
}

// 查询周报表详细
export function getWeek(id) {
  return request({
    url: '/report/week/' + id,
    method: 'get'
  })
}

// 新增周报表
export function addWeek(data) {
  return request({
    url: '/report/week',
    method: 'post',
    data: data
  })
}

// 修改周报表
export function updateWeek(data) {
  return request({
    url: '/report/week',
    method: 'put',
    data: data
  })
}

// 删除周报表
export function delWeek(id) {
  return request({
    url: '/report/week/' + id,
    method: 'delete'
  })
}
