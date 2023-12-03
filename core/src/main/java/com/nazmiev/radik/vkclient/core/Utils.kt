package com.nazmiev.radik.vkclient.core

object Constants {
    const val ACCESS_TOKEN = "access_token"
    const val MIN_DELAY_SECONDS = "min_delay_seconds"
    const val MAX_DELAY_SECONDS = "max_delay_seconds"

    const val VK_API_VERSION = "5.154"

    const val TASK_TYPE_ACCEPT_FRIENDS_REQUESTS = 100

    const val TASK_STATUS_WAITING = 1
    const val TASK_STATUS_RUNNING = 2
    const val TASK_STATUS_PLANNED = 3

    const val CAPTCHA_DEFAULT_ANTIGATE_SERVICE = "default_antigate_service"
    const val CAPTCHA_RUCAPTCHA_KEY = "rucapcha_key"
    const val CAPTCHA_CPTCH_KEY = "cptch_key"
    const val CAPTCHA_ANTI_CAPTCHA_KEY = "anti_captcha_key"
    const val CAPTCHA_BOT_CAPTCHA_KEY = "3bot_captcha_key"
}

object RequestFields {
    const val PHOTO_50 = "photo_50"
    const val PHOTO_100 = "photo_100"
    const val PHOTO_200 = "photo_200"
    const val HAS_PHOTO = "has_photo"
    const val CAN_BE_INVITED_GROUP = "can_be_invited_group"
    const val CAN_SEND_FRIEND_REQUEST = "can_send_friend_request"
    const val CAN_WRITE_PRIVATE_MESSAGE = "can_write_private_message"
    const val BLACKLISTED = "blacklisted"
    const val BLACKLISTED_BY_ME = "blacklisted_by_me"
    const val SEX = "sex"
    const val BDATE = "bdate"
}