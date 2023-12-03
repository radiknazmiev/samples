package com.nazmiev.radik.vkclient.core.ui.fragments.acceptfriendstasksettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nazmiev.radik.vkclient.core.R
import com.nazmiev.radik.vkclient.core.http.models.User
import com.nazmiev.radik.vkclient.core.ui.common.CustomSlider
import com.nazmiev.radik.vkclient.core.ui.common.SwitchButton
import com.nazmiev.radik.vkclient.core.ui.common.UsersList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcceptFriendsTaskSettings(
    viewModel: AcceptFriendTaskSettingsViewModel = viewModel(),
    finish: (Boolean, Int, Boolean, Long, Boolean) -> Unit
) {
    val uiState = viewModel.state.collectAsState()
    val userCheck = { user: User ->
        viewModel.checkAccount(user)
    }

    val taskRepeatCheck = { isChecked: Boolean ->
        viewModel.saveRepeatTask(isChecked)
    }

    val sliderChange = { value: Float ->
        viewModel.saveRepeatPeriod(value)
    }

    val sendMessageCheck = { isChecked: Boolean ->
        viewModel.saveSendMessage(isChecked)
    }

    val taskName = stringResource(id = R.string.core_txt_accept_incoming_requests)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        finish(false, 0, false, 0, false)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.saveSelectedAccounts(taskName)
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
                SettingsView(
                    uiState = state,
                    paddingValues = innerPadding,
                    userCheck,
                    taskRepeatCheck,
                    sliderChange,
                    sendMessageCheck
                )

            }

            is State.Finish -> {
                val state = (uiState.value as State.Finish)
                finish(true, state.taskId, state.isRepeat, state.repeatPeriod, state.isSendMessage)
            }
        }
    }
}

@Composable
fun SettingsView(
    uiState: State.UsersSuccess,
    paddingValues: PaddingValues,
    userCheck: (User) -> Unit,
    taskRepeatCheck: (isChecked: Boolean) -> Unit,
    sliderChange: (position: Float) -> Unit,
    sendMessageCheck: (isChecked: Boolean) -> Unit
) {
    Column(
        modifier = Modifier.padding(paddingValues)
            .padding(start = 10.dp, end = 10.dp)
    ) {
        SwitchButton(
            text = stringResource(id = R.string.core_txt_repeat_task),
            checking = taskRepeatCheck,
            isChecked = uiState.isRepeat
        )
        if (uiState.isRepeat) {
            CustomSlider(
                position = uiState.repeatPeriod,
                range = 0F..7F,
                steps = 6,
                text = "days",
                onValueChange = sliderChange
            )
        }
        Row(
            modifier = Modifier.padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.core_txt_send_accompanying_messages))
            Spacer(
                Modifier
                    .weight(1f)
            )
            Checkbox(
                checked = uiState.isSendMessage,
                onCheckedChange = sendMessageCheck
            )
        }
        UsersList(uiState.users, userCheck)
    }
}

