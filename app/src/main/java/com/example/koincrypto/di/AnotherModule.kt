package com.example.koincrypto.di

import com.example.koincrypto.view.ListFragment
import org.koin.core.qualifier.named
import org.koin.dsl.module

val anotherModule = module {

    scope<ListFragment> {
        scoped(qualifier = named("hello")) {
            "Hello Kotlin"
        }
        scoped(qualifier = named("hi")) {
            "Hi Kotlin"
        }
    }
}