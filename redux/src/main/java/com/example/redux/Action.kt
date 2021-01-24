package com.example.redux

import kotlinx.coroutines.flow.Flow

typealias Action<Intents, Action> = (intents: Intents) -> Flow<Action>