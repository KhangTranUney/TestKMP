package com.example.foo.testkmp

import kotlinx.serialization.Serializable

@Serializable
object MainRoute

@Serializable
object DemoRoute

@Serializable
object DemoScreenARoute

@Serializable
object DemoScreenBRoute

@Serializable
data class DemoScreenCRoute(val itemId: Int)
