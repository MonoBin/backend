package sh.monobin.core.controllers

import sh.monobin.core.requests.PasteCreateRequest
import sh.monobin.core.entities.PasteEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.github.oshai.kotlinlogging.KotlinLogging
import sh.monobin.core.exceptions.InvalidPasteRequestException
import sh.monobin.core.exceptions.PasteNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import sh.monobin.core.repositories.PasteRepository

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/pastes")
class PasteController(private val pasteRepository: PasteRepository) {

    @PostMapping("/create")
    fun createPaste(@RequestBody pasteCreateRequest: PasteCreateRequest): PasteEntity {
        if (pasteCreateRequest.title.isBlank() || pasteCreateRequest.content.isBlank()) {
            log.error { "Title and content cannot be empty" }
            throw InvalidPasteRequestException("Title and content cannot be empty")
        }

        val paste = pasteRepository.save(
            PasteEntity(
                id = pasteCreateRequest.id,
                pasteID = pasteCreateRequest.pasteID,
                title = pasteCreateRequest.title,
                content = pasteCreateRequest.content,
                createdAt = pasteCreateRequest.createdAt
            )
        )
        log.info { "Paste created with ID: ${paste.pasteID}" }
        return paste
    }

    @GetMapping("/{pasteID}")
    fun getPasteByPasteID(@PathVariable pasteID: String): PasteEntity =
        pasteRepository.findByPasteID(pasteID).also { log.info { "Request for paste with ID: $pasteID" } }
            ?: throw PasteNotFoundException("Paste not found").also { log.error { "Paste not found with ID: $pasteID" } }

    @DeleteMapping("/{pasteID}")
    fun deletePasteByPasteID(@PathVariable pasteID: String): ResponseEntity<Any> {
        log.info { "Request to delete paste with ID: $pasteID" }
        val paste = pasteRepository.findByPasteID(pasteID) ?: throw PasteNotFoundException("Paste not found")
        pasteRepository.delete(paste)
        log.info { "Paste deleted with ID: $pasteID" }
        return ResponseEntity.ok("Paste deleted")
    }
}