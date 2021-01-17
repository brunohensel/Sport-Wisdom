package com.example.redux

typealias Reducer<State, Action> = (currentState: State, action: Action) -> State