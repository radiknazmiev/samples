package com.nazmiev.radik.vkclient.core.repositories

import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.db.models.GroupsForPosting
import com.nazmiev.radik.vkclient.core.db.models.GroupsForProcessing
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.GroupResponse
import javax.inject.Inject

class GroupsForProcessingRepositoryImpl @Inject constructor(
    private val db: AppDatabase
): GroupsForProcessingRepository {

    override lateinit var httpService: HttpService

    override suspend fun getGroups(pid: Int): List<GroupsForProcessing> {
        return db.groupsForProcessingDao().getGroups(pid)
    }

    override suspend fun searchGroups(options: Map<String, String>): GroupResponse {
        return httpService.searchGroups(options)
    }

    override suspend fun getGroupsFromFile(pid: Int): List<GroupsForPosting> {
        return db.groupsForPostingDao().getGroups(pid)
    }
}