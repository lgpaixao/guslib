package com.gustavo.guslib.events

import com.gustavo.guslib.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent (
    source: Any,
    val purchaseModel: PurchaseModel
): ApplicationEvent(source)