package app.devzenix.docscan.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.devzenix.docscan.presentation.main.DocumentStatusFilter
import app.devzenix.docscan.presentation.theme.LocalExtendedColors
import org.jetbrains.compose.resources.stringResource
import app.devzenix.docscan.shared.generated.resources.Res
import app.devzenix.docscan.shared.generated.resources.add_document
import app.devzenix.docscan.shared.generated.resources.empty_active_subtitle
import app.devzenix.docscan.shared.generated.resources.empty_active_title
import app.devzenix.docscan.shared.generated.resources.empty_expired_subtitle
import app.devzenix.docscan.shared.generated.resources.empty_expired_title
import app.devzenix.docscan.shared.generated.resources.empty_list_subtitle
import app.devzenix.docscan.shared.generated.resources.empty_list_title
import app.devzenix.docscan.shared.generated.resources.empty_urgent_subtitle
import app.devzenix.docscan.shared.generated.resources.empty_urgent_title

@Composable
internal fun FilterEmptyState(
    filter: DocumentStatusFilter,
    hasAnyDocuments: Boolean,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accentRed = LocalExtendedColors.current.accentRed

    val (title, subtitle) = when {
        !hasAnyDocuments -> Pair(
            stringResource(Res.string.empty_list_title),
            stringResource(Res.string.empty_list_subtitle)
        )
        filter == DocumentStatusFilter.ACTIVE -> Pair(
            stringResource(Res.string.empty_active_title),
            stringResource(Res.string.empty_active_subtitle)
        )
        filter == DocumentStatusFilter.URGENT -> Pair(
            stringResource(Res.string.empty_urgent_title),
            stringResource(Res.string.empty_urgent_subtitle)
        )
        filter == DocumentStatusFilter.EXPIRED -> Pair(
            stringResource(Res.string.empty_expired_title),
            stringResource(Res.string.empty_expired_subtitle)
        )
        else -> Pair(
            stringResource(Res.string.empty_list_title),
            stringResource(Res.string.empty_list_subtitle)
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            if (!hasAnyDocuments) {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onAddClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentRed
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "+ ${stringResource(Res.string.add_document)}",
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
        }
    }
}
