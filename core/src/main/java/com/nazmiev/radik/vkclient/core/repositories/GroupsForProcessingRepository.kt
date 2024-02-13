package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.models.GroupsForPosting
import com.nazmiev.radik.vkclient.core.db.models.GroupsForProcessing
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.GroupResponse

interface GroupsForProcessingRepository {

    var httpService: HttpService

    suspend fun getGroups(pid: Int): List<GroupsForProcessing>

    suspend fun searchGroups(options: Map<String, String>): GroupResponse

    suspend fun getGroupsFromFile(pid: Int): List<GroupsForPosting>
}