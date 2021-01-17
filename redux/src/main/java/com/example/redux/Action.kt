package com.example.redux

import kotlinx.coroutines.flow.Flow

typealias Action<Event, Action> = (event: Event) -> Flow<Action>