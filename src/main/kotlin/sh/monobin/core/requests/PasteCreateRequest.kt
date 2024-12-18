package sh.monobin.core.requests

import java.util.UUID

data class PasteCreateRequest(
    val id: Long,

    val pasteID: String = UUID.randomUUID().toString(),

    val title: String = "",

    val content: String = "",

    val createdAt: Long = System.currentTimeMillis()

)