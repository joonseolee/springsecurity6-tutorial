package com.joonseolee.security

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class Springsecurity6TutorialApplication

fun main(args: Array<String>) {
    runApplication<Springsecurity6TutorialApplication>(*args)
}