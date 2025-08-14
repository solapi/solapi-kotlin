package net.nurigo.sdk.message.exception

import net.nurigo.sdk.message.model.FailedMessage

class SolapiMessageNotReceivedException(message: String) : SolapiException,
    Exception(message) {
    var failedMessageList: List<FailedMessage> = emptyList()

}