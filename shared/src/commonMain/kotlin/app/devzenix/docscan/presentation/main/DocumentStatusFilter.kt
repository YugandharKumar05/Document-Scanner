package app.devzenix.docscan.presentation.main

import org.jetbrains.compose.resources.StringResource
import app.devzenix.docscan.shared.generated.resources.Res
import app.devzenix.docscan.shared.generated.resources.filter_active
import app.devzenix.docscan.shared.generated.resources.filter_all
import app.devzenix.docscan.shared.generated.resources.filter_expired
import app.devzenix.docscan.shared.generated.resources.filter_urgent

enum class DocumentStatusFilter(val labelRes: StringResource) {
    ALL(Res.string.filter_all),
    ACTIVE(Res.string.filter_active),
    URGENT(Res.string.filter_urgent),
    EXPIRED(Res.string.filter_expired)
}
