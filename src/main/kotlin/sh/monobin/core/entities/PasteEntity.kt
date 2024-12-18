package sh.monobin.core.entities

import jakarta.persistence.*

@Entity
@Table(name = "pastes")
class PasteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(nullable = false)
    val pasteID: String,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val content: String,

    @Column(nullable = false)
    val createdAt: Long
) {
    constructor() : this(0, "", "", "", 0)
}