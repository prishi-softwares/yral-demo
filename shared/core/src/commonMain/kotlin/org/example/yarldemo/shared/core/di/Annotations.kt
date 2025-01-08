package org.example.yarldemo.shared.core.di

import me.tatarka.inject.annotations.Qualifier
import me.tatarka.inject.annotations.Scope
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.TYPE
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER

@Qualifier
@Target(
  PROPERTY_GETTER,
  FUNCTION,
  VALUE_PARAMETER,
  TYPE
)
annotation class Named(val value: String)

@Scope
@Target(CLASS, FUNCTION, PROPERTY_GETTER)
annotation class Singleton
