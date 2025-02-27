package com.gustavo.guslib.model

import com.gustavo.guslib.enums.BookStatus
import com.gustavo.guslib.enums.Errors
import com.gustavo.guslib.exceptions.BadRequestException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.util.Optional

@Entity(name = "book")
data class BookModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
) {

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value){
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO)
                throw BadRequestException(Errors.GL102.message.format(field), Errors.GL102.code)
            field = value
        }

    constructor(id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?): this(id, name, price, customer){
            this.status = status
        }
}