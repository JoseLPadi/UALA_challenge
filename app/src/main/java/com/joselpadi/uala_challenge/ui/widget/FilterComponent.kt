package com.joselpadi.uala_challenge.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joselpadi.uala_challenge.R

@Composable
fun FilterWidget(onFilter: (String) -> Unit){
    FilterWidgetContent(onFilter)
}

@Composable
private fun FilterWidgetContent(onFilter: (String) -> Unit){
    var textFilter by remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = textFilter,
            onValueChange = { newFilter ->
                textFilter = newFilter
                onFilter(newFilter)
            },
            label = { Text("Filter City") },
            modifier = Modifier.padding(10.dp).weight(1f),
            singleLine = true
        )
        Icon(
            painter = painterResource(R.drawable.search),
            contentDescription = "search",
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Composable
@Preview
private fun FilterWidgetPreview(){
    FilterWidget { }
}