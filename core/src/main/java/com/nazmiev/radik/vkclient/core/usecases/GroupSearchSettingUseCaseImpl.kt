package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.db.models.GroupSearchSetting
import javax.inject.Inject

class GroupSearchSettingUseCaseImpl @Inject constructor(
    private val db: AppDatabase
): GroupSearchSettingUseCase {

    override fun getSetting(id: Int): GroupSearchSetting {
        return db.groupSearchSettingDao().getGroupSearchSetting(id)
    }

    override fun getAllSettings(): List<GroupSearchSetting> {
        return db.groupSearchSettingDao().getAllGroupSearchSettings()
    }
}