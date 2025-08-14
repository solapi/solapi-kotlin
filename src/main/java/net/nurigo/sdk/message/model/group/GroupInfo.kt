package net.nurigo.sdk.message.model.group

import kotlinx.serialization.Contextual
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupInfo(
    var count: GroupCount? = null,
    var countForCharge: CountForCharge? = null,
    var balance: GroupBalance? = null,
    var point: GroupPoint? = null,
    var app: AppInfo? = null,
    var serviceMethod: String? = null,
    var sdkVersion: String? = null,
    var osPlatform: String? = null,
    var log: List<GroupLog>? = null,
    var status: String? = null,
    var scheduledDate: String? = null,
    var isRefunded: Boolean? = null,
    var flagUpdated: Boolean? = null,
    var prepaid: Boolean? = null,
    var strict: Boolean? = null,
    var masterAccountId: String? = null,
    var allowDuplicates: Boolean? = null,
    @SerialName("_id")
    var id: String? = null,
    var accountId: String? = null,
    var apiVersion: String? = null,
    var customFields: Map<String, String>? = null,
    var groupId: String? = null,
    var price: Map<String, PriceInfoDetail>? = null,

    @Contextual
    var dateSent: LocalDateTime? = null,
    @Contextual
    var dateCreated: LocalDateTime? = null,
    @Contextual
    var dateUpdated: LocalDateTime? = null,
    @Contextual
    var dateCompleted: LocalDateTime? = null
)