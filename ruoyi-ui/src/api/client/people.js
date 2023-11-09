import request from '@/utils/request'

// 查询客户列表
export function listPeople(query) {
  return request({
    url: '/client/people/list',
    method: 'get',
    params: query
  })
}

// 查询客户详细
export function getPeople(userId) {
  return request({
    url: '/client/people/' + userId,
    method: 'get'
  })
}

// 新增客户
export function addPeople(data) {
  return request({
    url: '/client/people',
    method: 'post',
    data: data
  })
}

// 修改客户
export function updatePeople(data) {
  return request({
    url: '/client/people',
    method: 'put',
    data: data
  })
}

// 删除客户
export function delPeople(userId) {
  return request({
    url: '/client/people/' + userId,
    method: 'delete'
  })
}
