package com.nazmiev.radik.vkclient.core.ui.fragments.posting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nazmiev.radik.vkclient.core.R
import com.nazmiev.radik.vkclient.core.db.models.GroupSearchSetting
import com.nazmiev.radik.vkclient.core.http.models.User
import com.nazmiev.radik.vkclient.core.ui.common.CustomSlider
import com.nazmiev.radik.vkclient.core.ui.common.SingleSelectDialog
import com.nazmiev.radik.vkclient.core.ui.common.SwitchButton
import com.nazmiev.radik.vkclient.core.ui.common.UserItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostingTaskSettings(
    postingTaskSettingsViewModel: PostingTaskSettingsViewModel = viewModel(),
    finish: (Boolean, Int, Boolean, Long, Int) -> Unit
) {

    val uiState = postingTaskSettingsViewModel.state.collectAsState()
    var openDialog by remember {
        mutableStateOf(false)
    }

    val userCheck = { user: User ->
        postingTaskSettingsViewModel.checkAccount(user)
    }

    val taskRepeatCheck = { isChecked: Boolean ->
        postingTaskSettingsViewModel.saveRepeatTask(isChecked)
    }

    val sliderChange = { value: Float ->
        postingTaskSettingsViewModel.saveRepeatPeriod(value)
    }

    val selectSetting = { setting: Pair<Int, String> ->
        openDialog = false
        postingTaskSettingsViewModel.saveSelectedSettingId(setting.first)
    }

    val dismissDialog = { openDialog = false}
    val changeDialogState = { openDialog = !openDialog }

    val taskName = stringResource(id = R.string.core_txt_accept_incoming_requests)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        finish(false, 0, false, 0, 0)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        postingTaskSettingsViewModel.saveSelectedAccounts(taskName)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (uiState.value) {
            is State.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    CircularProgressIndicator()
                }

            }

            is State.UsersSuccess -> {
                val state = (uiState.value as State.UsersSuccess)
                val settings = postingTaskSettingsViewModel.postingSettings
                val selectedSetting = settings.map { Pair(it.id, it.settingTitle?:"") }.find { postingTaskSettingsViewModel.getSelectedSettingId() == it.first }

                SettingsView(
                    uiState = state,
                    paddingValues = innerPadding,
                    groupSearchSettings = settings,
                    openDialog,
                    selectedSetting?: Pair(0, ""),
                    userCheck,
                    taskRepeatCheck,
                    sliderChange,
                    selectSetting,
                    dismissDialog,
                    changeDialogState
                )

            }

            is State.Finish -> {
                val state = (uiState.value as State.Finish)
                finish(true, state.taskId, state.isRepeat, state.repeatPeriod, postingTaskSettingsViewModel.getSelectedSettingId())
            }
        }
    }
}

@Composable
fun SettingsView(
    uiState: State.UsersSuccess,
    paddingValues: PaddingValues,
    groupSearchSettings: List<GroupSearchSetting>,
    openDialog: Boolean,
    selectedSetting: Pair<Int, String>,
    userCheck: (User) -> Unit,
    taskRepeatCheck: (isChecked: Boolean) -> Unit,
    sliderChange: (position: Float) -> Unit,
    selectSetting: (setting: Pair<Int, String>) -> Unit,
    dismissDialog: () -> Unit,
    changeDialogState: () -> Unit
) {

    if (openDialog && groupSearchSettings.isNotEmpty()) {
        SingleSelectDialog(
            items = groupSearchSettings.map { Pair(it.id, it.settingTitle?:"") },
            dialogTitle = "Выберите настройку",
            submitButtonText = "Выбрать",
            onSubmitButtonClick = selectSetting,
            onDismissRequest = dismissDialog,
            selectedItem = selectedSetting
        )
    }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.core_txt_select_settings),
                Modifier.clickable {
                        changeDialogState.invoke()

                }
            )
            SwitchButton(
                text = stringResource(id = R.string.core_txt_repeat_task),
                checking = taskRepeatCheck,
                isChecked = uiState.isRepeat
            )
            if (uiState.isRepeat) {
                CustomSlider(
                    position = uiState.repeatPeriod,
                    range = 0F..360F,
                    steps = 11,
                    text = pluralStringResource(id = R.plurals.plurals_minutes, uiState.repeatPeriod.toInt(), uiState.repeatPeriod.toInt()),
                    onValueChange = sliderChange
                )
            }
        }
        items(items = uiState.users, key = null) { user ->
            UserItem(user = user, userCheck)
        }
    }
}

@Preview
@Composable
fun PostingTaskSettingsPreview() {
    PostingTaskSettings(finish = { withResult: Boolean, taskId: Int, isRepeatTask: Boolean, repeatPeriod: Long, setingId: Int ->})
}