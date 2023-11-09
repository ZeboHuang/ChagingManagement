import request from '@/utils/request'

// 查询优惠券列表
export function listVoucher(query) {
  return request({
    url: '/discount/voucher/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券详细
export function getVoucher(discountId) {
  return request({
    url: '/discount/voucher/' + discountId,
    method: 'get'
  })
}

// 新增优惠券
export function addVoucher(data) {
  return request({
    url: '/discount/voucher',
    method: 'post',
    data: data
  })
}

// 修改优惠券
export function updateVoucher(data) {
  return request({
    url: '/discount/voucher',
    method: 'put',
    data: data
  })
}

// 删除优惠券
export function delVoucher(discountId) {
  return request({
    url: '/discount/voucher/' + discountId,
    method: 'delete'
  })
}
