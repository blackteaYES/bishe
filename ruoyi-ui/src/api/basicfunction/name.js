import request from '@/utils/request'

// 查询设备类别列表
export function listName(query) {
  return request({
    url: '/system/name/list',
    method: 'get',
    params: query
  })
}

// 查询设备类别详细
export function getName(enId) {
  return request({
    url: '/system/name/' + enId,
    method: 'get'
  })
}

// 新增设备类别
export function addName(data) {
  return request({
    url: '/system/name',
    method: 'post',
    data: data
  })
}

// 修改设备类别
export function updateName(data) {
  return request({
    url: '/system/name',
    method: 'put',
    data: data
  })
}

// 删除设备类别
export function delName(enId) {
  return request({
    url: '/system/name/' + enId,
    method: 'delete'
  })
}

// 导出设备类别
export function exportName(query) {
  return request({
    url: '/system/name/export',
    method: 'get',
    params: query
  })
}