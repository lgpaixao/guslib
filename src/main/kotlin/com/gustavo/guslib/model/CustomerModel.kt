package com.gustavo.guslib.model

import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.enums.Profile
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column
    val password: String,

    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER)
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var roles: Set<Profile> = setOf()
)