package sh.monobin.core.repositories

import sh.monobin.core.entities.PasteEntity
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface PasteRepository : JpaRepository<PasteEntity, Long> {
    fun findByPasteID(pasteID: String): PasteEntity?
    fun findByTitle(title: String): PasteEntity?
    fun findByContent(content: String): PasteEntity?
    fun findByCreatedAt(createdAt: Long): PasteEntity?
}