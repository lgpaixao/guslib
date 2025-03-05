package com.gustavo.guslib

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class GuslibApplication

fun main(args: Array<String>) {
	runApplication<GuslibApplication>(*args)
}


//TODO: Adicionar a criação automática do banco de dados caso não exista