package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.GroupsForPosting
import com.nazmiev.radik.vkclient.core.db.models.GroupsForProcessing
import com.nazmiev.radik.vkclient.core.http.models.GroupResponse

interface GroupsForProcessingUseCase {

    suspend fun getGroups(pid: Int): List<GroupsForProcessing>

    suspend fun searchGroups(
        searchQuery: String,
        accessToken: String,
        groupType: Int,
        cityId: Int,
        sort: Int,
        count: Int,
        offset: Int
    ): GroupResponse

    suspend fun getGroupsFromFile(pid: Int): List<GroupsForPosting>
}