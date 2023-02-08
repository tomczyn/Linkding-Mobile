package com.tomczyn.linkding.android.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tomczyn.linkding.android.ui.common.theme.Dimens

@Composable
fun MarginSpacer() {
    Spacer(modifier = Modifier.padding(Dimens.margin))
}
