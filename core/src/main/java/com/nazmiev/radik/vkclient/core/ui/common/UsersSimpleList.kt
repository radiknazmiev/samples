package com.nazmiev.radik.vkclient.core.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nazmiev.radik.vkclient.core.http.models.User

@Composable
internal fun UserItem(user: User, userCheck: (User) -> Unit) {
    val fullName = "${user.firstName} ${user.lastName}"
    val note = user.note

    Card(
        modifier = Modifier.clickable {
            userCheck(user)
        },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.photo50,
                contentDescription = "",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = fullName,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(value = 14F, type = TextUnitType.Sp)
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = note,
                    fontSize = TextUnit(value = 12F, type = TextUnitType.Sp)
                )
            }
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            Checkbox(
                checked = user.isChecked,
                onCheckedChange = { userCheck(user) }
            )
        }
    }
}