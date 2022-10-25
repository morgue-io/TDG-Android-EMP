package com.thebobighat.emp

/* Dependencies begin */

data class GetOrderResponsePayloadItemStatusGenericAssignee(
    var state: Boolean,
    var time: String,
    var assignee_id: String,
    var assignee_name: String,
    var verif_code: String
)

data class GetOrderResponsePayloadItemStatusGenericNoAssignee(
    var state: Boolean,
    var time: String
)

data class GetOrderResponsePayloadItemStatus(
    var accepted: GetOrderResponsePayloadItemStatusGenericNoAssignee,
    var picked_up: GetOrderResponsePayloadItemStatusGenericAssignee,
    var processing: GetOrderResponsePayloadItemStatusGenericNoAssignee,
    var delivered: GetOrderResponsePayloadItemStatusGenericAssignee
)

data class GetOrderResponsePayloadItem(
    var _id: String,
    var customer_id: String,
    var customer_name: String,
    var address: String,
    var todo: CartInstance,
    var status: GetOrderResponsePayloadItemStatus,
    var createdAt: String,
    var updatedAt: String
)

data class TodoInstance(
    var formal_wash: List<Serviceable>,
    var dry_wash: List<Serviceable>,
    var steam_iron: List<Serviceable>
)

data class GetTasksResponseObj(
    var type: String,
    var customer_id: String,
    var customer_name: String,
    var address: String,
    var verif_code: String,
    var todo: TodoInstance
)

/* Dependencies end */


data class LoginResponse(
    var success: Boolean,
    var message: String,
    var jwt: String,
    var jwt_refresh: String
)

data class GenericResponse(
    var success: Boolean,
    var message: String
)

data class GetOrdersResponse(
    var success: Boolean,
    var message: String,
    var payload: List<GetOrderResponsePayloadItem>
)

data class GetTasksResponse(
    var success: Boolean,
    var message: String,
    var payload: List<GetTasksResponseObj>
)

data class GetServiceHistoryResponse(
    var success: Boolean,
    var message: String,
    var payload: List<String>
)