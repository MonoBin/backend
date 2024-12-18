package sh.monobin.core.controllers

import sh.monobin.core.exceptions.InvalidPasteRequestException
import sh.monobin.core.exceptions.PasteNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {

    @ExceptionHandler
    fun handlePasteNotFoundException(e: PasteNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }

    @ExceptionHandler
    fun handleInvalidPasteRequestException(e: InvalidPasteRequestException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
    }

}