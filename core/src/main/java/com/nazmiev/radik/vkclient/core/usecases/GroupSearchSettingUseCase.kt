package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.GroupSearchSetting

interface GroupSearchSettingUseCase {

    fun getSetting(id: Int): GroupSearchSetting

    fun getAllSettings(): List<GroupSearchSetting>
}