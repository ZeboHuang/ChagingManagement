import request from '@/utils/request'

// 查询日报表列表
export function listDay(query) {
  return request({
    url: '/report/day/list',
    method: 'get',
    params: query
  })
}

// 查询日报表详细
export function getDay(id) {
  return request({
    url: '/report/day/' + id,
    method: 'get'
  })
}

// 新增日报表
export function addDay(data) {
  return request({
    url: '/report/day',
    method: 'post',
    data: data
  })
}

// 修改日报表
export function updateDay(data) {
  return request({
    url: '/report/day',
    method: 'put',
    data: data
  })
}

// 删除日报表
export function delDay(id) {
  return request({
    url: '/report/day/' + id,
    method: 'delete'
  })
}
