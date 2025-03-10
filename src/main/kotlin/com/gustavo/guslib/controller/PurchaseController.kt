package com.gustavo.guslib.controller

import com.gustavo.guslib.controller.mapper.PurchaseMapper
import com.gustavo.guslib.controller.request.PostPurchaseRequest
import com.gustavo.guslib.model.PurchaseModel
import com.gustavo.guslib.service.PurchaseService
import jakarta.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/purchases")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest){
        purchaseService.create(purchaseMapper.toModel(request))
    }

    @GetMapping("/{customerId}")
    fun getPurchases(@PathVariable customerId: Int): List<PurchaseModel> {
        var purchases = purchaseService.getById(customerId)
        return purchases
    }
}