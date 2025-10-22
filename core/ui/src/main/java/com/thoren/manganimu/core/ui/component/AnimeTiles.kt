package com.thoren.manganimu.core.ui.component

import PreviewTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoren.manganimu.core.ui.common.SpaceSize
import com.thoren.manganimu.core.ui.theme.typography

@Composable
fun BasicAnimeTile(
    title: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val tileWidth = 120.dp
    val tileHeight = 240.dp

    Column(
        modifier = modifier
            .size(width = tileWidth, height = tileHeight)
            .clip(shape = RoundedCornerShape(SpaceSize.small))
            .clickable { onClick?.invoke() },
    ) {
        AsyncImage(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(SpaceSize.small)),
            contentScale = ContentScale.Crop,
            model = imageUrl,
            contentDescription = null
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black.copy(alpha = 0.5f))
        ) {
            Text(
                text = title,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SpaceSize.tiny, vertical = SpaceSize.tiny)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BasicAnimeTilePreview() {
    PreviewTheme {
        BasicAnimeTile(
            title = "My hero academia",
            imageUrl = "https://cdn.myanimelist.net/images/anime/13/17405.jpg"
        )
    }
}