import request from '@/utils/request'

// 查询设备列表
export function listEquipment(query) {
  return request({
    url: '/basicfunction/equipment/list',
    method: 'get',
    params: query
  })
}

// 查询设备详细
export function getEquipment(meId) {
  return request({
    url: '/basicfunction/equipment/' + meId,
    method: 'get'
  })
}

// 新增设备
export function addEquipment(data) {
  return request({
    url: '/basicfunction/equipment',
    method: 'post',
    data: data
  })
}

// 修改设备
export function updateEquipment(data) {
  return request({
    url: '/basicfunction/equipment',
    method: 'put',
    data: data
  })
}

// 删除设备
export function delEquipment(meId) {
  return request({
    url: '/basicfunction/equipment/' + meId,
    method: 'delete'
  })
}

// 导出设备
export function exportEquipment(query) {
  return request({
    url: '/basicfunction/equipment/export',
    method: 'get',
    params: query
  })
}