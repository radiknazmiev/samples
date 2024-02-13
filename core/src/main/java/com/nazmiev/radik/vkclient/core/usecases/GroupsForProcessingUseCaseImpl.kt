package com.nazmiev.radik.vkclient.core.usecases

import com.nazmiev.radik.vkclient.core.db.models.GroupsForPosting
import com.nazmiev.radik.vkclient.core.db.models.GroupsForProcessing
import com.nazmiev.radik.vkclient.core.http.HttpService
import com.nazmiev.radik.vkclient.core.http.models.GroupResponse
import com.nazmiev.radik.vkclient.core.repositories.GroupsForProcessingRepository
import com.nazmiev.radik.vkclient.core.utils.Constants
import javax.inject.Inject
import javax.inject.Named

class GroupsForProcessingUseCaseImpl @Inject constructor(
    private val groupsForProcessingRepository: GroupsForProcessingRepository,
    @Named("VKAPI") private val httpService: HttpService
) : GroupsForProcessingUseCase {

    init {
        groupsForProcessingRepository.httpService = httpService
    }

    override suspend fun getGroups(pid: Int): List<GroupsForProcessing> {
        return groupsForProcessingRepository.getGroups(pid)
    }

    override suspend fun searchGroups(
        searchQuery: String,
        accessToken: String,
        groupType: Int,
        cityId: Int,
        sort: Int,
        count: Int,
        offset: Int
    ): GroupResponse {
        val type = when (groupType) {
            0 -> "group"
            1 -> "page"
            2 -> "event"
            else -> "group"
        }

        val queryMap = object : HashMap<String, String>() {
            init {
                put("access_token", accessToken)
                put("v", Constants.VK_API_VERSION)
                put("q", searchQuery)
                put("type", type)
                put("city_id", cityId.toString())
                put("count", count.toString())
                put("offset", offset.toString())
            }
        }

        return groupsForProcessingRepository.searchGroups(queryMap)
    }

    override suspend fun getGroupsFromFile(pid: Int): List<GroupsForPosting> {
        return groupsForProcessingRepository.getGroupsFromFile(pid)
    }
}